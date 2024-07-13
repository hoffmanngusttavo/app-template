package br.com.hoffmann.model.service;

import br.com.hoffmann.model.entity.Produto;
import br.com.hoffmann.model.service.generic.GenericCrudService;


public interface ProdutoService extends GenericCrudService<Produto> {


    void inativar(Long id);

    void ativar(Long id);
}
