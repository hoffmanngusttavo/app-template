package br.com.hoffmann.model.component.converter;

import br.com.hoffmann.model.dto.CategoriaProdutoDTO;
import br.com.hoffmann.model.dto.input.CategoriaProdutoInputDTO;
import br.com.hoffmann.model.entity.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ProdutoConverter {

    public CategoriaProdutoDTO converterEntidadeParaDTO(Produto categoria){
        return CategoriaProdutoDTO.builder()
                .criadoPor(categoria.getCreatedBy())
                .dataCadastro(categoria.getCreated())
                .nome(categoria.getNome())
                .ativo(categoria.isAtivo())
                .build();
    }

    public Produto converterDTOParaEntidade(CategoriaProdutoInputDTO dto) {
        var categoria = new Produto();
        categoria.setNome(dto.getNome());
        categoria.setAuditoria(dto.getAuditoria());
        return categoria;
    }
}
