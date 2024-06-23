package br.com.hoffmann.model.crons;

import br.com.hoffmann.model.component.CnabComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduleRemoveFilesTMP {


    private static final String TIME_ZONE = "America/Sao_Paulo";

    @Autowired
    private CnabComponent component;

    //@Scheduled(cron = "*/30 * * * * *", zone = TIME_ZONE)
    @Scheduled(cron = "0 0 8 ? * *", zone = TIME_ZONE)
    public void executar() {
        component.removerRegistrosTMP();
    }


}
