package br.com.hoffmann.web.controller;

import br.com.hoffmann.model.dto.CategoriaProdutoDTO;
import br.com.hoffmann.model.dto.input.CategoriaProdutoInputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "Categorias de produtos")
public interface CategoriaProdutoController {


     @Operation(summary = "Buscar pelo id")
     ResponseEntity<CategoriaProdutoDTO> buscarPorId(Long id);

     @Operation(summary = "Cadastrar novo")
     ResponseEntity<Long> cadastrarNovo(CategoriaProdutoInputDTO dto, UriComponentsBuilder uriBuilder);

     @Operation(summary = "Buscar todas as categorias")
     ResponseEntity<Page<CategoriaProdutoDTO>> listar(Pageable paginacao);

     @Operation(summary = "Inativar pelo id")
     ResponseEntity<Void> inativar(Long id);

     @Operation(summary = "Ativar pelo id")
     ResponseEntity<Void> ativar(Long id);


}
