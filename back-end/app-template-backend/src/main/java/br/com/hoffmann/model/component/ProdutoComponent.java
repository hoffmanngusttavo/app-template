package br.com.hoffmann.model.component;

import br.com.hoffmann.model.component.converter.ProdutoConverter;
import br.com.hoffmann.model.dto.CategoriaProdutoDTO;
import br.com.hoffmann.model.dto.input.CategoriaProdutoInputDTO;
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

    public CategoriaProdutoDTO buscarPorId(Long id) {
        var entity = service.findById(id);
        return converter.converterEntidadeParaDTO(entity);
    }

    @Transactional
    public Long cadastrar(CategoriaProdutoInputDTO dto) {
       var novoRegistro = converter.converterDTOParaEntidade(dto);
       var categoriaSalvo = service.save(novoRegistro);
       return categoriaSalvo.getId();
    }

    public Page<CategoriaProdutoDTO> listarTodos(Pageable paginacao) {
        var page = service.findAllPageable(paginacao);
        return page.map(converter::converterEntidadeParaDTO);
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
