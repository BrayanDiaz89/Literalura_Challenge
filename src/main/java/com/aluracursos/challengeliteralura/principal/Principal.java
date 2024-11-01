package com.aluracursos.challengeliteralura.principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Principal {

    @Autowired
    private final MetodosPrincipal metodo;

    public Principal(MetodosPrincipal metodo) {
        this.metodo = metodo;
    }

    public void primerMetodo(){
        metodo.getDatosLibro();
    }
}
