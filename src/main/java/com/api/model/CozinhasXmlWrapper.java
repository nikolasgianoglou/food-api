package com.api.model;

import com.api.food.domain.model.Cozinha;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@JsonRootName("cozinhas")
@Data
public class CozinhasXmlWrapper {

    @JsonProperty("cozinha")
    @JacksonXmlElementWrapper(useWrapping = false)
    @NonNull//O Lombok gera os contrutores de prop nao nulas, anotando essa prop com @NonNull ele gera o construtor para mim
    private List<Cozinha> cozinhas;
}
