package br.com.hoffmann.web.controller;

import br.com.hoffmann.model.dto.cnab.TransacaoReport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Transações financeiras")
public interface TransacaoController {

    @Operation(summary = "Registros agrupados")
    ResponseEntity<List<TransacaoReport>> totalizadores();

    @Operation(summary = "Relatório impresso em pdf, xls, csv, doc")
    ResponseEntity<Resource> relatorio(String fileType) throws Exception;


}
