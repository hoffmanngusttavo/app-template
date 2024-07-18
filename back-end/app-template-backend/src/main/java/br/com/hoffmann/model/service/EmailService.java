package br.com.hoffmann.model.service;

import br.com.hoffmann.model.dto.input.EmailDTO;

public interface EmailService {

    void sendEmail(EmailDTO dto);

}
