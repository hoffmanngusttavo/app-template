package br.com.hoffmann.model.publisher;

import br.com.hoffmann.model.dto.input.EmailQueueDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailPublisher {

    @Value("${spring.rabbitmq.queue.email}")
    private String queueNameEmail;

    private final RabbitTemplate rabbitTemplate;

    public void sendEmailQueue(EmailQueueDTO emailDto) {
        rabbitTemplate.convertAndSend(queueNameEmail, emailDto);
    }


}
