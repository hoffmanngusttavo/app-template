package br.com.hoffmann.model.component.converter;

import br.com.hoffmann.model.dto.UsuarioDTO;
import br.com.hoffmann.model.dto.input.UsuarioInputDTO;
import br.com.hoffmann.model.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UsuarioConverter {

    public UsuarioDTO converterEntidadeParaDTO(Usuario usuario){
        return UsuarioDTO.builder()
                .criadoPor(usuario.getDadosAuditoria().getCreatedBy())
                .dataCadastro(usuario.getDadosAuditoria().getCreated())
                .login(usuario.getLogin())
                .senha(usuario.getSenha())
                .build();
    }

    public Usuario converterDTOParaEntidade(UsuarioInputDTO dto) {
        return Usuario.builder()
                .login(dto.getLogin())
                .senha(dto.getSenha())
                .dadosAuditoria(dto.getAuditoria())
                .build();
    }
}
