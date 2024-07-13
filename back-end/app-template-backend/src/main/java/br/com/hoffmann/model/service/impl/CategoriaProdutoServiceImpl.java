package br.com.hoffmann.model.service.impl;

import br.com.hoffmann.model.entity.CategoriaProduto;
import br.com.hoffmann.model.repository.CategoriaProdutoRepository;
import br.com.hoffmann.model.service.CategoriaProdutoService;
import br.com.hoffmann.model.service.generic.impl.GenericCrudServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CategoriaProdutoServiceImpl extends GenericCrudServiceImpl<CategoriaProduto> implements CategoriaProdutoService {


    @Override
    @Transactional
    public void inativar(Long id) {
        ((CategoriaProdutoRepository) repository).inativar(id);
    }

    @Override
    @Transactional
    public void ativar(Long id) {
        ((CategoriaProdutoRepository) repository).ativar(id);
    }

}
