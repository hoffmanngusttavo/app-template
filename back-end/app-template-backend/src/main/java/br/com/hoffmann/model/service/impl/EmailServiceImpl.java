package br.com.hoffmann.model.service.impl;

import br.com.hoffmann.model.dto.input.EmailDTO;
import br.com.hoffmann.model.dto.input.EmailQueueDTO;
import br.com.hoffmann.model.publisher.EmailPublisher;
import br.com.hoffmann.model.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailPublisher producer;

    @Override
    public void sendEmail(EmailDTO dto) {
        var requestDTO = new EmailQueueDTO();
        BeanUtils.copyProperties(dto, requestDTO);
        producer.sendEmailQueue(requestDTO);
    }
}
