package com.api.food.jpa;

import com.api.FoodApiApplication;
import com.api.food.domain.model.Restaurante;
import com.api.food.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ExclusaoRestauranteMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
        Restaurante restaurante = new Restaurante();
        restaurante.setId(1L);

        restauranteRepository.deleteById(restaurante.getId());
    }
}
