package com.api.food.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data //@Getter, @Setter, @ToString, @EqualsAndHashCode
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table
public class Cozinha {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;
}
