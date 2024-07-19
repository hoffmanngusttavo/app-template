package br.com.hoffmann.model.service.impl;

import br.com.hoffmann.model.dto.cnab.TransacaoReport;
import br.com.hoffmann.model.entity.TransacaoCNAB;
import br.com.hoffmann.model.repository.TransacaoRepository;
import br.com.hoffmann.model.service.TransacaoCnabService;
import br.com.hoffmann.model.service.generic.impl.GenericCrudServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TransacaoCnabServiceImpl extends GenericCrudServiceImpl<TransacaoCNAB, TransacaoRepository> implements TransacaoCnabService {


    public TransacaoCnabServiceImpl(TransacaoRepository repository) {
        super(repository);
    }

    @Override
    public List<TransacaoReport> getTotaisTransacoesPorLoja() {

        var listaTransacoes = repository.findAllByOrderByNomeDaLojaAscIdDesc();

        var reportMap = new LinkedHashMap<String, TransacaoReport>();

        listaTransacoes.forEach(transacao ->{

            var nomeDaLoja = transacao.getNomeDaLoja();
            var valor = transacao.getValor();

            reportMap.compute(nomeDaLoja, (key, existingReport) -> {
                var report = existingReport != null
                        ? existingReport
                        : new TransacaoReport(key, BigDecimal.ZERO, new ArrayList<>());
                return report.addTotal(valor).addTransacao(transacao);
            });

        });

        return new ArrayList<>(reportMap.values());
    }




}
