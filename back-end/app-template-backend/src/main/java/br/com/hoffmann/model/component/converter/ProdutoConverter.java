package br.com.hoffmann.model.component.converter;

import br.com.hoffmann.model.dto.input.ProdutoInputDTO;
import br.com.hoffmann.model.entity.CategoriaProduto;
import br.com.hoffmann.model.entity.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ProdutoConverter {

    public Produto converterDTOParaEntidade(ProdutoInputDTO dto) {
        var categoria = new Produto();
        categoria.setNome(dto.getNome());
        categoria.setAuditoria(dto.getAuditoria());
        categoria.setCategoriaProduto(new CategoriaProduto(dto.getIdCategoriaProduto()));
        return categoria;
    }
}
