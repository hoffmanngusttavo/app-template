package br.com.hoffmann.model.dto.input;

import br.com.hoffmann.model.entity.base.DadosAuditoria;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoInputDTO implements Serializable {


    @NotBlank(message = "{nome.obrigatorio}")
    @Size(min = 2, max = 250, message = "{min.max.nome.caracteres}")
    private String nome;

    @NotBlank(message = "{criadopor.obrigatorio}")
    private String criadoPor;

    @NotNull
    private Long idCategoriaProduto;

    @JsonIgnore
    public DadosAuditoria getAuditoria() {
        return DadosAuditoria.builder()
                .createdBy(criadoPor)
                .updatedBy(criadoPor)
                .build();
    }


}
