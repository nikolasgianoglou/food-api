package com.api.food.domain.service;

import com.api.food.domain.exception.EntidadeNaoEncontradaException;
import com.api.food.domain.model.Cidade;
import com.api.food.domain.model.Cozinha;
import com.api.food.domain.model.Estado;
import com.api.food.domain.repository.CidadeRepository;
import com.api.food.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade salvar(Cidade cidade){
        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoRepository.findById(estadoId).orElseThrow(
                () -> new EntidadeNaoEncontradaException(
                        String.format(
                                "Não existe cadastro de estado com código %d", estadoId
                        )
        ));
        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    public void excluir(Long id) {
        try {
            cidadeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de cidade com código %d", id)
            );
        }
    }
}
