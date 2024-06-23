package br.com.hoffmann.model.repository;

import br.com.hoffmann.model.entity.TransacaoCNAB;
import br.com.hoffmann.model.repository.generic.GenericCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends GenericCrudRepository<TransacaoCNAB> {


    List<TransacaoCNAB> findAllByOrderByNomeDaLojaAscIdDesc();
}
