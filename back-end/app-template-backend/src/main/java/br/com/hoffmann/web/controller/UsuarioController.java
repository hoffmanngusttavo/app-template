package br.com.hoffmann.web.controller;

import br.com.hoffmann.model.dto.UsuarioDTO;
import br.com.hoffmann.model.dto.input.UsuarioInputDTO;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "Usuários")
public interface UsuarioController  {


     @Operation(summary = "Buscar pelo id")
     ResponseEntity<UsuarioDTO> findById(Long id);

     @Operation(summary = "Cadastrar novo")
     ResponseEntity<Long> cadastrarNovo(UsuarioInputDTO dto, UriComponentsBuilder uriBuilder);


     @Operation(summary = "Buscar todos os usuários")
     ResponseEntity<Page<UsuarioDTO>> lista(Pageable paginacao);


     /**
      * @Hidden esconde no swagger o endpoint
      * */
     @Hidden
     @Operation(summary = "Remover pelo id")
     ResponseEntity<Void> deleteById(Long id);

}
