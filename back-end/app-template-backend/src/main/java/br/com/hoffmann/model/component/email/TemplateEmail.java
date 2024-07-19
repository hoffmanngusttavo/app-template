package br.com.hoffmann.model.component.email;

import br.com.hoffmann.model.dto.input.EmailDTO;
import br.com.hoffmann.model.service.EmailService;
import br.com.hoffmann.model.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public abstract class TemplateEmail {


    @Autowired
    private EmailService service;


    public void sendEmail(EmailTemplateDTO templateDTO)  {

        var contentEmail = new EmailDTO();
        contentEmail.setEmailFrom("hoffmann.gusttavo@gmail.com");
        contentEmail.setEmailTo(templateDTO.getEmailTo());
        contentEmail.setSubject(this.getSubject());
        contentEmail.setMessage(formatTemplateEmail(templateDTO.getItens()));

        service.sendEmail(contentEmail);
    }


    public abstract String getFileTemplateEmail();

    public abstract String getSubject();

    private String formatTemplateEmail(Map<String, String> itens) {

        String template = null;
        try {
            template = this.loadTemplate();
        } catch (IOException e) {
            throw new ServiceException(e);
        }

        for (Map.Entry<String, String> item : itens.entrySet()) {
            var key = this.formatKey(item.getKey());
            var value = item.getValue();
            template = template.replaceAll(key, value);
        }

        return template;
    }

    private String formatKey(String key) {
        return String.format("#{%s}", key);
    }


    private String loadTemplate() throws IOException {
        ClassPathResource resource = new ClassPathResource(this.getPathFileName());
        return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }

    private String getPathFileName(){
        return "templates/html/"+getFileTemplateEmail();
    }






}
