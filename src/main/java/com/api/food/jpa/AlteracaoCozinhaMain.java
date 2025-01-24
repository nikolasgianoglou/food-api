package com.api.food.jpa;

import com.api.food.FoodApiApplication;
import com.api.food.domain.model.Cozinha;
import com.api.food.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class AlteracaoCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);
        cozinha.setNome ("Brasileira");

        cozinhaRepository.salvar(cozinha);
        System.out.printf("%d - %s\n", cozinha.getId(), cozinha.getNome());
    }
}
