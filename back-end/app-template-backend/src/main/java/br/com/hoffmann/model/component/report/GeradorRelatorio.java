package br.com.hoffmann.model.component.report;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeradorRelatorio<T> {

    private Map<String, Object> parametros = new HashMap<>();
    private final String templateJrxml;
    private final List<T> dataSource;
    private final TipoExtensaoArquivo reportType;

    public GeradorRelatorio (String template, List<T> collectionBeans, TipoExtensaoArquivo reportType){
        templateJrxml = template;
        dataSource = collectionBeans;
        this.reportType = reportType;
    }

    public GeradorRelatorio addParameter(String key, Object value){
        parametros.put(key, value);
        return this;
    }

    public DownloadFile gerarArquivo(String nomeArquivo) throws Exception{
        byte[] conteudo = exportar();
        return new DownloadFile(conteudo, nomeArquivo, reportType);
    }


    private byte[] exportar() throws Exception{
        var filePath = ResourceUtils.getFile("classpath:" + templateJrxml).getAbsolutePath();
        var jasperReport = JasperCompileManager.compileReport(filePath);

        var beanCollectionDataSource = new JRBeanCollectionDataSource(dataSource);

        var jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, beanCollectionDataSource);

        var byteArrayOutputStream = new ByteArrayOutputStream();

        switch (reportType){
            case XML -> JasperExportManager.exportReportToXmlStream(jasperPrint, byteArrayOutputStream);
            case XLSX -> this.exportReportXlsxStream(jasperPrint, byteArrayOutputStream);
            case CSV -> this.exportReportCSVStream(jasperPrint, byteArrayOutputStream);
            case HTML -> this.exportReportHtmlStream(jasperPrint, byteArrayOutputStream);
            case DOC -> this.exportReportDocStream(jasperPrint, byteArrayOutputStream);

            default ->  JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);
        }

        return byteArrayOutputStream.toByteArray();
    }


    private void exportReportDocStream(JasperPrint jasperPrint, OutputStream outputStream) throws JRException {
        JRRtfExporter docxExporter = new JRRtfExporter();
        docxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        docxExporter.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
        docxExporter.exportReport();
    }

    private void exportReportHtmlStream(JasperPrint jasperPrint, OutputStream outputStream) throws JRException {
        HtmlExporter htmlExporter = new HtmlExporter();
        htmlExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        htmlExporter.setExporterOutput(new SimpleHtmlExporterOutput(outputStream));
        htmlExporter.exportReport();
    }



    private void exportReportCSVStream(JasperPrint jasperPrint, OutputStream outputStream) throws JRException {
        JRCsvExporter csvExporter = new JRCsvExporter();
        csvExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        csvExporter.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
        csvExporter.exportReport();
    }

    private void exportReportXlsxStream(JasperPrint jasperPrint, OutputStream outputStream) throws JRException, IOException {
        JRXlsxExporter xlsxExporter = new JRXlsxExporter();

        try(OutputStream xlsReport = outputStream){
            jasperPrint.setProperty(net.sf.jasperreports.engine.JRParameter.IS_IGNORE_PAGINATION, "true");
            xlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
            SimpleXlsxReportConfiguration xlsxreportConfig = new SimpleXlsxReportConfiguration();
            xlsxreportConfig.setSheetNames(new String[] { "Some Report" });
            xlsxreportConfig.setRemoveEmptySpaceBetweenRows(true);
            xlsxreportConfig.setForcePageBreaks(false);
            xlsxreportConfig.setWrapText(false);
            xlsxreportConfig.setCollapseRowSpan(true);
            xlsxreportConfig.setDetectCellType(true);
            xlsxExporter.setConfiguration(xlsxreportConfig);
            xlsxExporter.exportReport();
        }

    }


}
