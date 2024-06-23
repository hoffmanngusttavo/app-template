package br.com.hoffmann.model.component;

import br.com.hoffmann.model.component.report.ConstanteReports;
import br.com.hoffmann.model.component.report.DownloadFile;
import br.com.hoffmann.model.component.report.GeradorRelatorio;
import br.com.hoffmann.model.component.report.TipoExtensaoArquivo;
import br.com.hoffmann.model.dto.cnab.TransacaoReport;
import br.com.hoffmann.model.entity.TransacaoCNAB;
import br.com.hoffmann.model.service.TransacaoCnabService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TransacaoCnabComponent {

    private final TransacaoCnabService service;

    public List<TransacaoReport> getTotaisTransacoesPorLoja(){
        return service.getTotaisTransacoesPorLoja();
    }


    public DownloadFile gerarRelatorio(TipoExtensaoArquivo reportType) throws Exception {

        List<TransacaoCNAB> lista = new ArrayList<>();
        var jrxmlArquivo = ConstanteReports.TRANSACAO_CNAB;
        var prefixoNomeArquivo = "transacao";

        var geradorRelatorio = new GeradorRelatorio(jrxmlArquivo, lista, reportType);

        return geradorRelatorio.gerarArquivo(prefixoNomeArquivo);
    }
}
