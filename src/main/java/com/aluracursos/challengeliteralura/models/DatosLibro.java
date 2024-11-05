package com.aluracursos.challengeliteralura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("languages") List<String> lenguaje,
        @JsonAlias("formats") Formats poster,
        @JsonAlias("download_count") Double numeroDeDescargas,
        @JsonAlias("authors") List<DatosAutor> autores
) {}

