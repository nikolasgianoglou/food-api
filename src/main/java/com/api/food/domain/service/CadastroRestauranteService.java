package com.api.food.domain.service;

import com.api.food.domain.exception.EntidadeNaoEncontradaException;
import com.api.food.domain.model.Cozinha;
import com.api.food.domain.model.Restaurante;
import com.api.food.domain.repository.CozinhaRepository;
import com.api.food.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
        if (cozinha == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format(
                            "Não existe cadastro de cozinha com código %d", cozinhaId
                    )
            );
        }
        restaurante.setCozinha(cozinha);
        return restauranteRepository.salvar(restaurante);
    }
}
