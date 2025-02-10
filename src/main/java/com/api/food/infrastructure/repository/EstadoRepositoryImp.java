package com.api.food.infrastructure.repository;

import com.api.food.domain.exception.EntidadeNaoEncontradaException;
import com.api.food.domain.model.Estado;
import com.api.food.domain.repository.EstadoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EstadoRepositoryImp implements EstadoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Estado> listar() {
        TypedQuery<Estado> query= manager.createQuery("from Estado", Estado.class);
        return query.getResultList();
    }

    @Override
    public Estado buscar(Long id) {
        return manager.find(Estado.class, id);
    }

    @Transactional
    @Override
    public Estado salvar(Estado estado) {
        return manager.merge(estado);
    }

    @Transactional
    @Override
    public void remover(Long id) {
        Estado estado = buscar(id);

        if (estado == null) {
            throw new EmptyResultDataAccessException(1);
        }

        manager.remove(estado);
    }
}
