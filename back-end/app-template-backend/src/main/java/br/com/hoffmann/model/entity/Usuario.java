package br.com.hoffmann.model.entity;


import br.com.hoffmann.model.entity.base.BaseEntity;
import br.com.hoffmann.model.entity.base.DadosAuditoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@Entity
@Table(name = "USUARIO")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Version
    private Long version;

    @Embedded
    private DadosAuditoria dadosAuditoria;




}
