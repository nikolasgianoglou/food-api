package com.api.food.infrastructure.repository;

import com.api.food.domain.model.Restaurante;
import com.api.food.domain.repository.RestauranteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestauranteRepositoryImp implements RestauranteRepository {

    @PersistenceContext
    private EntityManager manager;;

    @Override
    public List<Restaurante> listar() {
        TypedQuery<Restaurante> query = manager.createQuery("from Restaurante ", Restaurante.class);
        return query.getResultList();
    }

    @Override
    public Restaurante buscar(Long id) {
        return manager.find(Restaurante.class, id);
    }

    @Transactional
    @Override
    public Restaurante salvar(Restaurante restaurante) {
        return manager.merge(restaurante);
    }

    @Transactional
    @Override
    public void remover(Restaurante restaurante) {
        restaurante = buscar(restaurante.getId());
        manager.remove(restaurante);
    }
}
