package br.com.hoffmann.model.service;

import br.com.hoffmann.model.dto.cnab.TransacaoReport;
import br.com.hoffmann.model.entity.TransacaoCNAB;
import br.com.hoffmann.model.service.generic.GenericCrudService;

import java.util.List;


public interface TransacaoCnabService extends GenericCrudService<TransacaoCNAB> {

    List<TransacaoReport> getTotaisTransacoesPorLoja();

}
