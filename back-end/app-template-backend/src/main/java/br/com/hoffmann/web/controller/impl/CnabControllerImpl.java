package br.com.hoffmann.web.controller.impl;

import br.com.hoffmann.model.component.CnabComponent;
import br.com.hoffmann.web.controller.CnabController;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cnab")
public class CnabControllerImpl implements CnabController {


    private final CnabComponent component;


    @PostMapping(value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> importarArquivo(@RequestParam("file") MultipartFile file) throws Exception {
        component.updloadCnabFile(file);
        return ResponseEntity.ok("Processamento iniciado com sucesso");
    }

}
