package br.com.hoffmann.model.repository;

import br.com.hoffmann.model.entity.Produto;
import br.com.hoffmann.model.repository.generic.GenericCrudRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends GenericCrudRepository<Produto> {


    @Modifying
    @Query("UPDATE Produto set ativo = false where id = ?1")
    void inativar(Long id);

    @Modifying
    @Query("UPDATE Produto set ativo = true where id = ?1")
    void ativar(Long id);
}
