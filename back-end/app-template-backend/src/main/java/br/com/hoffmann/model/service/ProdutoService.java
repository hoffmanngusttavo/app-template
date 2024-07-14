package br.com.hoffmann.model.service;

import br.com.hoffmann.model.dto.ProdutoDTO;
import br.com.hoffmann.model.entity.Produto;
import br.com.hoffmann.model.service.generic.GenericCrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProdutoService extends GenericCrudService<Produto> {


    void inativar(Long id);

    void ativar(Long id);

    ProdutoDTO getInfosById(Long id);

    Page<ProdutoDTO> getAllPageable(Pageable paginacao);
}
