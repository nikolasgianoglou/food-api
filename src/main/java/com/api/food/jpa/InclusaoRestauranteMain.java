package com.api.food.jpa;

import com.api.FoodApiApplication;
import com.api.food.domain.model.Restaurante;
import com.api.food.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

public class InclusaoRestauranteMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
        Restaurante restaurante1 = new Restaurante();
        restaurante1.setNome ("Brasileira");
        restaurante1.setTaxaFrete(new BigDecimal("1000"));
        Restaurante restaurante2 = new Restaurante();
        restaurante2.setNome ("Japonesa");
        restaurante2.setTaxaFrete(new BigDecimal("2000"));
        restaurante1 = restauranteRepository.save(restaurante1);
        restaurante2 = restauranteRepository.save(restaurante2);

        System.out.println();
        System.out.printf("%d - %s\n", restaurante1.getId(), restaurante1.getNome() );
        System.out.printf ("%d - %s\n", restaurante2.getId() , restaurante2.getNome() );
    }
}
