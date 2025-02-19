package com.api.food.infrastructure.repository;

import com.api.food.domain.model.Restaurante;
import com.api.food.domain.repository.RestauranteRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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

    public List<Restaurante> findDynamic(String nome,
                                         BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

        var jpql = new StringBuilder();

        jpql.append("from Restaurante where 0 = 0 ");

        var parametros = new HashMap<String, Object>();

        if (StringUtils.hasLength(nome)) {
            jpql.append("and nome like :nome ");
            parametros.put("nome", "%" + nome + "%");
        }

        if (taxaFreteInicial != null) {
            jpql.append("and taxaFrete >= :taxaInicial");
            parametros. put ("taxaInicial", taxaFreteInicial);
        }

        if (taxaFreteFinal != null) {
            jpql.append("and taxaFrete <= :taxaFinal");
            parametros.put ("taxaFinal", taxaFreteFinal);
        }

        TypedQuery<Restaurante> query =  manager.createQuery(jpql.toString(), Restaurante.class);

        parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
        return query.getResultList();
    }

    //Criteria API > API do JPA para criacao de queries de forma programatica

    public List<Restaurante> findWithCriteriaAPI(String nome,
                                                 BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
        Root<Restaurante> root = criteria.from(Restaurante.class); //from Restaurante

        var predicates = new ArrayList<Predicate>();

        if (StringUtils.hasText(nome)) {
            predicates.add(builder. like(root.get ("nome"), "%" + nome + "%"));
        }

        if (taxaFreteInicial != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
        }

        if (taxaFreteFinal != null) {
            builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal);
        }
        
        criteria.where(predicates.toArray(new Predicate[0])); //como esse metodo aceita um array eu uso o toArray, passando uma instancia de um novo array vazio do tipo que quero

        TypedQuery<Restaurante> restauranteTypedQuery =  manager.createQuery(criteria);
        return restauranteTypedQuery.getResultList();
    }

}
