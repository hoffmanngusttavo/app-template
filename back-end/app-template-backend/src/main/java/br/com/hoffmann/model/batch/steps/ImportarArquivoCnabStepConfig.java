package br.com.hoffmann.model.batch.steps;

import br.com.hoffmann.model.dto.cnab.TransacaoCNABDTO;
import br.com.hoffmann.model.dto.cnab.TransacaoDTO;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ImportarArquivoCnabStepConfig {

    private final PlatformTransactionManager transactionManager;
    private final JobRepository jobRepository;
    private final ItemReader<TransacaoCNABDTO> importarArquivoCnabReader;
    private final ItemProcessor<TransacaoCNABDTO, TransacaoDTO> importarArquivoCnabProcessor;
    private final ItemWriter<TransacaoDTO> importarArquivoCnabWriter;


    public ImportarArquivoCnabStepConfig(PlatformTransactionManager transactionManager,
                                         JobRepository jobRepository,
                                         @Qualifier("importarArquivoCnabReader") ItemReader<TransacaoCNABDTO> importarArquivoCnabReader,
                                         @Qualifier("importarArquivoCnabProcessor") ItemProcessor<TransacaoCNABDTO, TransacaoDTO> importarArquivoCnabProcessor,
                                         @Qualifier("importarArquivoCnabWriter") ItemWriter<TransacaoDTO> importarArquivoCnabWriter) {
        this.transactionManager = transactionManager;
        this.jobRepository = jobRepository;
        this.importarArquivoCnabReader = importarArquivoCnabReader;
        this.importarArquivoCnabProcessor = importarArquivoCnabProcessor;
        this.importarArquivoCnabWriter = importarArquivoCnabWriter;
    }

    @Bean
    Step importarArquivoCnabStep(){
        return new StepBuilder("importarArquivoCnabStep", jobRepository)
                .<TransacaoCNABDTO, TransacaoDTO>chunk(1000, transactionManager)
                .reader(importarArquivoCnabReader)
                .processor(importarArquivoCnabProcessor)
                .writer(importarArquivoCnabWriter)
                .build();
    }


}
