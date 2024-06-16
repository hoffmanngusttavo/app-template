package br.com.hoffmann.model.dto.input;

import br.com.hoffmann.model.entity.base.DadosAuditoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioInputDTO implements Serializable {


    @NotBlank(message = "{login.obrigatorio}")
    @Size(min = 2, max = 20, message = "{min.max.login.caracteres}")
    private String login;

    @NotBlank(message = "{senha.obrigatorio}")
    @Size(min = 6, max = 20, message = "{min.max.senha.caracteres}")
    private String senha;

    @NotBlank(message = "{criadopor.obrigatorio}")
    private String criadoPor;

    public DadosAuditoria getAuditoria() {
        return DadosAuditoria.builder()
                .createdBy(criadoPor)
                .updatedBy(criadoPor)
                .build();
    }


}
