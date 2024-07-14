package br.com.hoffmann.web.controller;

import br.com.hoffmann.model.dto.ProdutoDTO;
import br.com.hoffmann.model.dto.input.ProdutoInputDTO;
import br.com.hoffmann.model.dto.response.PageableResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "Produtos")
public interface ProdutoController {


     @Operation(summary = "Buscar pelo id")
     ResponseEntity<ProdutoDTO> buscarPorId(Long id);

     @Operation(summary = "Cadastrar novo")
     ResponseEntity<Long> cadastrarNovo(ProdutoInputDTO dto, UriComponentsBuilder uriBuilder);

     @Operation(summary = "Buscar todos os produtos")
     ResponseEntity<PageableResponseDTO> listar(Pageable paginacao);

     @Operation(summary = "Inativar pelo id")
     ResponseEntity<Void> inativar(Long id);

     @Operation(summary = "Ativar pelo id")
     ResponseEntity<Void> ativar(Long id);


}
