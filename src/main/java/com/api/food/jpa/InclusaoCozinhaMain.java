package com.api.food.jpa;

import com.api.FoodApiApplication;
import com.api.food.domain.model.Cozinha;
import com.api.food.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InclusaoCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome ("Brasileira");
        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome ("Japonesa");
        cozinha1 = cozinhaRepository.save(cozinha1);
        cozinha2 = cozinhaRepository.save (cozinha2);

        System.out.println();
        System.out.printf("%d - %s\n", cozinha1.getId(), cozinha1.getNome() );
        System.out.printf ("%d - %s\n", cozinha2.getId() , cozinha2.getNome() );
    }
}
