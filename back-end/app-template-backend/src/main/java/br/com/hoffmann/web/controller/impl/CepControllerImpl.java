package br.com.hoffmann.web.controller.impl;

import br.com.hoffmann.model.component.CepComponent;
import br.com.hoffmann.model.dto.DadosEnderecoDetalhe;
import br.com.hoffmann.web.controller.CepController;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cep")
public class CepControllerImpl implements CepController {

    private final CepComponent component;

    @Override
    @GetMapping("/{cep}")
    public ResponseEntity<DadosEnderecoDetalhe> buscarCep(@PathVariable("cep") String cep) {
        return  ResponseEntity.ok()
                .body(component.buscarCep(cep));
    }




}
