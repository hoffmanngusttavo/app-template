package br.com.hoffmann.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Cnab")
public interface CnabController {

    @Operation(summary = "Importar Cnab para processamento em blocos")
    ResponseEntity<String> importarArquivo(MultipartFile file) throws Exception;

}
