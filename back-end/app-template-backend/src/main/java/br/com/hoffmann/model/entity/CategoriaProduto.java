package br.com.hoffmann.model.entity;

import br.com.hoffmann.model.entity.base.BaseEntity;
import br.com.hoffmann.model.entity.base.DadosAuditoria;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "CATEGORIA_PRODUTO")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CategoriaProduto implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Embedded
    private DadosAuditoria auditoria;

    private boolean ativo = true;

    public String getCreatedBy() {
        return auditoria.getCreatedBy();
    }

    public LocalDateTime getCreated() {
        return auditoria.getCreated();
    }
}
