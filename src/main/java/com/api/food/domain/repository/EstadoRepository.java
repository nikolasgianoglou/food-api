package com.api.food.domain.repository;

import com.api.food.domain.model.Cidade;
import com.api.food.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {
    List<Estado> listar();
    Estado buscar(Long id);
    Estado salvar(Estado estado);
    void remover(Long id);
}
