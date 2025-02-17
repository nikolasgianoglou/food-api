package com.api.food.infrastructure.repository;

import com.api.food.domain.model.Restaurante;
import com.api.food.domain.repository.RestauranteRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/*
A classe deve ter o sufixo Impl para o spring encontrar a implementacao concreta do metodo.
Nesse caso eu declaro a assinatura do metodo em minha interface e o spring consegue identificar essa classe com a
implementação concreta
 */
@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> find(String nome,
                                  BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

    var jpql = "from Restaurante where nome like :nome "
            + "and taxaFrete between : taxaInicial and :taxaFinal";
    return manager.createQuery(jpql, Restaurante.class)
            .setParameter("nome", "%" + nome + "%")
            .setParameter("taxaInicial", taxaFreteInicial)
            .setParameter("taxaFinal", taxaFreteFinal)
            .getResultList();
    }

}
