package com.api.food.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "taxa_frete", nullable = false) //caso taxa frete nao possa ser nulo
    private BigDecimal taxaFrete;

    @ManyToOne /* Muitos restaurantes possuem uma cozinha */
    @JoinColumn(name="cozinha_id", nullable = false) // Nesse caso se  eu quiser um nome de coluna diferente eu uso
    private Cozinha cozinha;

    @OneToMany
    private List<FormaPagamento> formaPagamento;
}
