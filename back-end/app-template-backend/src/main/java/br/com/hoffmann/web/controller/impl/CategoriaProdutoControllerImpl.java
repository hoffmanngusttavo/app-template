package br.com.hoffmann.web.controller.impl;

import br.com.hoffmann.model.component.CategoriaProdutoComponent;
import br.com.hoffmann.model.dto.CategoriaProdutoDTO;
import br.com.hoffmann.model.dto.input.CategoriaProdutoInputDTO;
import br.com.hoffmann.web.controller.CategoriaProdutoController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/categoria-produto")
public class CategoriaProdutoControllerImpl implements CategoriaProdutoController {

    private final CategoriaProdutoComponent component;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProdutoDTO> buscarPorId(@PathVariable("id") Long id) {
        return  ResponseEntity.ok()
                .body(component.buscarPorId(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<Long> cadastrarNovo(@Valid @RequestBody CategoriaProdutoInputDTO dto, UriComponentsBuilder uriBuilder) {
        var codigo = component.cadastrar(dto);
        var uri = uriBuilder.path("/api/v1/categoria-produto/{id}").buildAndExpand(codigo).toUri();
        return ResponseEntity.created(uri).body(codigo);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<CategoriaProdutoDTO>> listar(@PageableDefault(size = 15, sort = {"nome"}) Pageable paginacao) {
        var page = component.listarTodos(paginacao);
        return ResponseEntity.ok(page);
    }

    @Override
    @PutMapping("/inativar/{id}")
    public ResponseEntity<Void> inativar(@PathVariable("id") Long id) {
        component.inativar(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping("/ativar/{id}")
    public ResponseEntity<Void> ativar(@PathVariable("id") Long id) {
        component.ativar(id);
        return ResponseEntity.ok().build();
    }

}
