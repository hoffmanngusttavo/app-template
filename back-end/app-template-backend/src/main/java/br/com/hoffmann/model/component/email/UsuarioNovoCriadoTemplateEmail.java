package br.com.hoffmann.model.component.email;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioNovoCriadoTemplateEmail extends TemplateEmail {


    @Override
    public String getFileTemplateEmail() {
        return "template-email-novo-usuario.html";
    }

    @Override
    public String getSubject() {
        return "Cadastro Usuário Novo";
    }
}
