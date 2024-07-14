package br.com.hoffmann.model.component;

import br.com.hoffmann.model.component.converter.ProdutoConverter;
import br.com.hoffmann.model.dto.ProdutoDTO;
import br.com.hoffmann.model.dto.input.ProdutoInputDTO;
import br.com.hoffmann.model.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProdutoComponent {

    private final ProdutoConverter converter;
    private final ProdutoService service;

    public ProdutoDTO buscarPorId(Long id) {
        return service.getInfosById(id);
    }

    @Transactional
    public Long cadastrar(ProdutoInputDTO dto) {
       var newItem = converter.converterDTOParaEntidade(dto);
       var saved = service.save(newItem);
       return saved.getId();
    }

    public Page<ProdutoDTO> listarTodos(Pageable paginacao) {
        return service.getAllPageable(paginacao);
    }

    @Transactional
    public void inativar(Long id) {
        service.inativar(id);
    }

    @Transactional
    public void ativar(Long id) {
        service.ativar(id);
    }
}
