package com.aluracursos.challengeliteralura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResultadoAPI(
        @JsonAlias("results") List<DatosLibro> libros
) {
}
