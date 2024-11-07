package com.aluracursos.challengeliteralura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Formats(
        @JsonAlias("image/jpeg") String poster,
        @JsonAlias("application/octet-stream") String libroElectronico
) {
}
