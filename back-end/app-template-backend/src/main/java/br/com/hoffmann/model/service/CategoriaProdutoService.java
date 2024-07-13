package br.com.hoffmann.model.service;

import br.com.hoffmann.model.entity.CategoriaProduto;
import br.com.hoffmann.model.service.generic.GenericCrudService;


public interface CategoriaProdutoService extends GenericCrudService<CategoriaProduto> {


    void inativar(Long id);

    void ativar(Long id);
}
