package br.com.hoffmann.model.component;

import br.com.hoffmann.model.component.converter.UsuarioConverter;
import br.com.hoffmann.model.component.email.EmailTemplateDTO;
import br.com.hoffmann.model.component.email.UsuarioNovoCriadoTemplateEmail;
import br.com.hoffmann.model.dto.UsuarioDTO;
import br.com.hoffmann.model.dto.input.UsuarioInputDTO;
import br.com.hoffmann.model.service.UsuarioService;
import br.com.hoffmann.model.service.exception.ItemAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UsuarioComponent {

    private final UsuarioConverter converter;
    private final UsuarioService service;
    private final UsuarioNovoCriadoTemplateEmail usuarioNovoCriadoTemplateEmail;

    public UsuarioDTO findById(Long id) {
        return converter.converterEntidadeParaDTO(service.findById(id));
    }

    @Transactional
    public Long salvarNovo(UsuarioInputDTO dto) {

        if(service.existLogin(dto.getLogin())){
            throw new ItemAlreadyExistException("Esse login já existe");
        }

       var usuario = converter.converterDTOParaEntidade(dto);

       var usuarioCriado = service.save(usuario);

        usuarioNovoCriadoTemplateEmail.sendEmail(new EmailTemplateDTO(usuario.getLogin())
                .addItemReplaceTemplate("nome", dto.getNome()));

       return usuarioCriado.getId();
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
