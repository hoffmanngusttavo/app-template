package br.com.hoffmann.model.component.converter;

import br.com.hoffmann.model.dto.CategoriaProdutoDTO;
import br.com.hoffmann.model.dto.input.CategoriaProdutoInputDTO;
import br.com.hoffmann.model.entity.CategoriaProduto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CategoriaProdutoConverter {

    public CategoriaProdutoDTO converterEntidadeParaDTO(CategoriaProduto categoria){
        return CategoriaProdutoDTO.builder()
                .criadoPor(categoria.getCreatedBy())
                .dataCadastro(categoria.getCreated())
                .nome(categoria.getNome())
                .ativo(categoria.isAtivo())
                .build();
    }

    public CategoriaProduto converterDTOParaEntidade(CategoriaProdutoInputDTO dto) {
        return CategoriaProduto.builder()
                .nome(dto.getNome())
                .auditoria(dto.getAuditoria())
                .build();
    }
}
