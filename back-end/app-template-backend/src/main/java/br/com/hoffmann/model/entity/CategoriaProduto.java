package br.com.hoffmann.model.entity;

import br.com.hoffmann.model.entity.base.BaseAudit;
import br.com.hoffmann.model.entity.base.BaseEntity;
import br.com.hoffmann.model.entity.base.DadosAuditoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "CATEGORIA_PRODUTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaProduto implements BaseEntity, BaseAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private boolean ativo = true;

    @Embedded
    private DadosAuditoria auditoria;

    public CategoriaProduto(Long id) {
        this.id = id;
    }

    @Override
    public String getCreatedBy() {
        return auditoria.getCreatedBy();
    }

    @Override
    public LocalDateTime getCreated() {
        return auditoria.getCreated();
    }

    @Override
    public String getLastUpdateBy() {
        return auditoria.getUpdatedBy();
    }

    @Override
    public LocalDateTime getUpdated() {
        return auditoria.getUpdated();
    }

}
