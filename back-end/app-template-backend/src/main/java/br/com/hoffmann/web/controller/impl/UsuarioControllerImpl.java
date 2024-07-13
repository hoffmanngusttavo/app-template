package br.com.hoffmann.web.controller.impl;

import br.com.hoffmann.model.component.UsuarioComponent;
import br.com.hoffmann.model.dto.UsuarioDTO;
import br.com.hoffmann.model.dto.input.UsuarioInputDTO;
import br.com.hoffmann.model.dto.response.PageableResponseDTO;
import br.com.hoffmann.web.controller.UsuarioController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/usuario")
public class UsuarioControllerImpl  implements UsuarioController {

    private final UsuarioComponent component;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable("id") Long id) {
        return  ResponseEntity.ok()
                .body(component.findById(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<Long> cadastrarNovo(@Valid @RequestBody UsuarioInputDTO dto, UriComponentsBuilder uriBuilder) {
        var codigo = component.salvarNovo(dto);
        var uri = uriBuilder.path("/api/v1/usuario/{id}").buildAndExpand(codigo).toUri();
        return ResponseEntity.created(uri).body(codigo);
    }

    @Override
    @GetMapping
    public ResponseEntity<PageableResponseDTO> lista(@PageableDefault(size = 15, sort = {"login"}) Pageable paginacao) {
        var page = component.listaPaginada(paginacao);
        return ResponseEntity.ok(new PageableResponseDTO(page));
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        component.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
