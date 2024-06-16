package br.com.hoffmann.model.entity;

import br.com.hoffmann.model.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "TRANSACAO")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TransacaoCNAB implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer tipo;

    private LocalDate data;

    private BigDecimal valor;

    private Long cpf;

    private String cartao;

    private LocalTime hora;

    @Column(name = "dono_loja")
    private String donoDaLoja;

    @Column(name = "nome_loja")
    private String nomeDaLoja;

}
