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
@Table(name = "PRODUTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto implements BaseEntity, BaseAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private boolean ativo = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "cod_categoria")
    private CategoriaProduto categoriaProduto;

    @Embedded
    private DadosAuditoria auditoria;

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
