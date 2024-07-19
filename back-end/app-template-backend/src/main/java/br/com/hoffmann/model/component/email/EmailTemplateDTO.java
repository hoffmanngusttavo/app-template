package br.com.hoffmann.model.component.email;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class EmailTemplateDTO {


    private final String emailTo;

    private final Map<String, String> itens = new HashMap<>();

    public EmailTemplateDTO(String emailTo) {
        this.emailTo = emailTo;
    }


    public EmailTemplateDTO addItemReplaceTemplate(String key, String value) {
        itens.put(key, value);
        return this;
    }
}
