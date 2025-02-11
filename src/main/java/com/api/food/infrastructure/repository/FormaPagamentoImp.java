package com.api.food.infrastructure.repository;

import com.api.food.domain.model.FormaPagamento;
import com.api.food.domain.repository.FormaPagamentoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
//public class FormaPagamentoImp implements FormaPagamentoRepository {
//
//    @PersistenceContext
//    private EntityManager manager;
//
//
//    @Override
//    public List<FormaPagamento> listar() {
//        TypedQuery<FormaPagamento> query = manager.createQuery("from FormaPagamento", FormaPagamento.class);
//        return query.getResultList();
//    }
//
//    @Override
//    public FormaPagamento buscar(Long id) {
//        return manager.find(FormaPagamento.class, id);
//    }
//
//    @Transactional
//    @Override
//    public FormaPagamento salvar(FormaPagamento formaPagamento) {
//        return manager.merge(formaPagamento);
//    }
//
//    @Transactional
//    @Override
//    public void remover(FormaPagamento formaPagamento) {
//        formaPagamento = buscar(formaPagamento.getId());
//        manager.remove(formaPagamento);
//    }
//}
