package br.com.hoffmann.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO implements Serializable {

    private Long id;
    private String nome;
    private boolean ativo;
    private String criadoPor;
    private LocalDateTime dataCadastro;
    private Long idCategoria;
    private String nomeCategoria;

}
