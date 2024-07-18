package br.com.hoffmann.model.component;

import br.com.hoffmann.model.component.converter.UsuarioConverter;
import br.com.hoffmann.model.dto.UsuarioDTO;
import br.com.hoffmann.model.dto.input.EmailDTO;
import br.com.hoffmann.model.dto.input.UsuarioInputDTO;
import br.com.hoffmann.model.service.EmailService;
import br.com.hoffmann.model.service.UsuarioService;
import br.com.hoffmann.model.service.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UsuarioComponent {

    private final UsuarioConverter converter;
    private final UsuarioService service;
    private final EmailService emailService;

    public UsuarioDTO findById(Long id) {
        return converter.converterEntidadeParaDTO(service.findById(id));
    }

    @Transactional
    public Long salvarNovo(UsuarioInputDTO dto) {
       var usuario = converter.converterDTOParaEntidade(dto);
       var usuarioCriado = service.save(usuario);

       var emailTemplate = new EmailDTO();
        emailTemplate.setEmailTo(usuario.getLogin());
        emailTemplate.setEmailFrom("ghoffmann@bionexo.com");
        emailTemplate.setSubject("Cadastro novo usuário");

        String templateHtml = null;
        try {
            templateHtml = carregaTemplateEmail();
        } catch (IOException e) {
            throw new ServiceException(e);
        }
        var templateFormatado = templateHtml.replace("#{nome}", usuarioCriado.getLogin());
        emailTemplate.setMessage(templateFormatado);

       emailService.sendEmail(emailTemplate);

       return usuarioCriado.getId();
    }


    public String carregaTemplateEmail() throws IOException {
        ClassPathResource resource = new ClassPathResource("templates/html/template-email-novo-usuario.html");
        return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }


    public Page<UsuarioDTO> listaPaginada(Pageable paginacao) {
        var page = service.findAllPageable(paginacao);
        return page.map(converter::converterEntidadeParaDTO);
    }

    @Transactional
    public void deleteById(Long id) {
        service.deleteById(id);
    }
}
