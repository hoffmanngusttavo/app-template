package br.com.hoffmann.model.repository;

import br.com.hoffmann.model.dto.ProdutoDTO;
import br.com.hoffmann.model.entity.Produto;
import br.com.hoffmann.model.repository.generic.GenericCrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends GenericCrudRepository<Produto> {


    @Modifying
    @Query("UPDATE Produto set ativo = false where id = ?1")
    void inativar(Long id);

    @Modifying
    @Query("UPDATE Produto set ativo = true where id = ?1")
    void ativar(Long id);

    @Query("""
            select new  br.com.hoffmann.model.dto.ProdutoDTO(
            p.id, p.nome, p.ativo, aud.createdBy, aud.created, cp.id, cp.nome)
            from Produto p
            join p.auditoria aud
            join p.categoriaProduto cp
            where p.id = ?1
            """)
    Optional<ProdutoDTO> getInfosById(Long id);

    @Query("""
            select new  br.com.hoffmann.model.dto.ProdutoDTO(
            p.id, p.nome, p.ativo, aud.createdBy, aud.created, cp.id, cp.nome)
            from Produto p
            join p.auditoria aud
            join p.categoriaProduto cp
            order by p.ativo
            """)
    Page<ProdutoDTO> getInfosPageable(Pageable pageable);
}
