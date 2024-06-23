package br.com.hoffmann.web.controller.impl;

import br.com.hoffmann.model.component.TransacaoCnabComponent;
import br.com.hoffmann.model.component.report.DownloadFile;
import br.com.hoffmann.model.component.report.TipoExtensaoArquivo;
import br.com.hoffmann.model.dto.cnab.TransacaoReport;
import br.com.hoffmann.web.controller.TransacaoController;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/transacao")
public class TransacaoControllerImpl implements TransacaoController {

    private final TransacaoCnabComponent component;


    @Override
    @GetMapping("/totalizadores")
    public ResponseEntity<List<TransacaoReport>> totalizadores() {
        return  ResponseEntity.ok()
                .body(component.getTotaisTransacoesPorLoja());
    }

    @Override
    @GetMapping("/relatorio/impressao")
    public ResponseEntity<Resource> relatorio(@RequestParam("fileType") String fileType) throws Exception {

        var reportType = TipoExtensaoArquivo.getReportTypeByName(fileType);

        DownloadFile file = component.gerarRelatorio(reportType);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + file.getNomeArquivo())
                .contentLength(file.getContentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(file.getResource());
    }


}
