package br.com.hoffmann.model.service.impl;

import br.com.hoffmann.model.dto.ProdutoDTO;
import br.com.hoffmann.model.entity.Produto;
import br.com.hoffmann.model.repository.ProdutoRepository;
import br.com.hoffmann.model.service.ProdutoService;
import br.com.hoffmann.model.service.exception.EntityNotFoundException;
import br.com.hoffmann.model.service.generic.impl.GenericCrudServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProdutoServiceImpl extends GenericCrudServiceImpl<Produto, ProdutoRepository> implements ProdutoService {


    public ProdutoServiceImpl(ProdutoRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public void inativar(Long id) {
        repository.inativar(id);
    }

    @Override
    @Transactional
    public void ativar(Long id) {
        repository.ativar(id);
    }

    @Override
    public ProdutoDTO getInfosById(Long id) {
        return repository.getInfosById(id)
                .orElseThrow(() -> new EntityNotFoundException("id not exists"));
    }

    @Override
    public Page<ProdutoDTO> getAllPageable(Pageable paginacao) {
        return repository.getInfosPageable(paginacao);
    }

}
