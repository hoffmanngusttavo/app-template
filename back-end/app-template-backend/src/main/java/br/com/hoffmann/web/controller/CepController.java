package br.com.hoffmann.web.controller;

import br.com.hoffmann.model.dto.DadosEnderecoDetalhe;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Cep")
public interface CepController {

    @Operation(summary = "Buscar dados do endereço pelo cep")
    ResponseEntity<DadosEnderecoDetalhe> buscarCep(@PathVariable("cep") String cep);

}
