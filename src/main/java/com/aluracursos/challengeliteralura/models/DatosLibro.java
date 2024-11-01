package com.aluracursos.challengeliteralura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
                        @JsonAlias("title") String titulo,
                        @JsonAlias("languages") String lenguaje,
                        @JsonAlias("image/jpeg") String poster,
                        @JsonAlias("download_count") Double numeroDeDescargas,
                        @JsonAlias("authors") List<Autor> authors
                        ){
}
