package com.aluracursos.challengeliteralura.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)//Columna unica porque no quiero libros repetidos (con el mismo nombre)
    private String titulo;
    private String lenguaje;
    private String poster;
    private Double numeroDeDescargas;
    //orphanRemoval = true, asegurará que si eliminamos un autor de la tabla autores, si este autor no tiene relación con ningun otro libro
    //se eliminará totalmente de la bd, para que no queden datos huerfanos.
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Autor> autores = new ArrayList<>();


    //JPA nos exige tener un constructor predeterminado con el mismo nombre del personalizado, ya él internamente
    //reconoce el personalizado que tenemos después.
    public Libro(){} //Constructor predeterminado.

    //Constructor personalizado.
    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.lenguaje = String.valueOf(datosLibro.lenguaje());
        this.poster = String.valueOf(datosLibro.poster());
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return  "|*°*°*°*°*°*°*°*°*°**°*°*°*°*°*°*°*°*°*°*°*°*°*°*°*°*°*°*°*°*°*°*°*|"+ '\'' +
                "| LIBRO |" + '\'' +
                " Título: " + titulo + '\'' +
                " Lenguaje: " + lenguaje + '\'' +
                " Poster: " + poster + '\'' +
                " Número de descargas:" + numeroDeDescargas + '\'' +
                " Autor: " + autores + '\'' +
                "|*°*°*°*°*°*°*°*°*°**°*°*°*°*°*°*°*°*°*°*°*°*°*°*°*°*°*°*°*°*°*°*°*|";
    }
}
