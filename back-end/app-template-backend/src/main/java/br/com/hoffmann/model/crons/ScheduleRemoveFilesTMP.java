package br.com.hoffmann.model.crons;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduleRemoveFilesTMP {


    private static final String TIME_ZONE = "America/Sao_Paulo";

    @Scheduled(cron = "*/30 * * * * *", zone = TIME_ZONE)
    //@Scheduled(cron = "0 0 8 ? * *", zone = TIME_ZONE)
    public void executar() {


        System.out.println("REMOVENDO ARQUIVOS");
    }


}
