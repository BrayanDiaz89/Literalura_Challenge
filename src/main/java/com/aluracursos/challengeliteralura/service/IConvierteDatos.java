package com.aluracursos.challengeliteralura.service;

import com.aluracursos.challengeliteralura.models.DatosLibro;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
