package br.com.hoffmann.model.batch.processors;

import br.com.hoffmann.model.dto.cnab.TransacaoCNABDTO;
import br.com.hoffmann.model.dto.cnab.TransacaoDTO;
import br.com.hoffmann.model.entity.TipoTransacao;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class ImportarArquivoCnabProcessorConfig {

    @Bean
    ItemProcessor<TransacaoCNABDTO, TransacaoDTO> importarArquivoCnabProcessor(){
        //wither pattern
        return item -> {

            var tipoTransacao = TipoTransacao.findByTipo(item.tipo());
            var valorFormatado = item.valor().multiply(tipoTransacao.getSinal());

            return new TransacaoDTO(null, item.tipo(),null,
                    valorFormatado.divide(BigDecimal.valueOf(100)),
                    item.cpf(), item.cartao(), null,
                    item.donoDaLoja().trim(), item.nomeDaLoja().trim())
                    .withData(item.data())
                    .withHour(item.hora());
        };
    }

}
