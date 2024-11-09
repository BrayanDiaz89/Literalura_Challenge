package com.aluracursos.challengeliteralura.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeDeceso;
    @ManyToMany
    @JoinTable(
            name = "autor_libro",
            joinColumns = @JoinColumn(name = "autor_id"),
            inverseJoinColumns = @JoinColumn(name = "libro_id")
    )
    private List<Libro> libros = new ArrayList<>();

    public void agregarLibro(Libro libro){
        if (!this.libros.contains(libro)) {
            this.libros.add(libro);
            libro.getAutores().add(this);
        }
    }

    public Autor(){}

    public Autor(String nombre, DatosAutor a){
        this.nombre = nombre;
        this.fechaDeNacimiento = a.anioNacimiento();
        this.fechaDeDeceso = a.anioDeceso();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFechaDeDeceso() {
        return fechaDeDeceso;
    }

    public void setFechaDeDeceso(Integer fechaDeDeceso) {
        this.fechaDeDeceso = fechaDeDeceso;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {

        return  "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n" +
                "|                                      | AUTOR: |\n" +
                "| Nombre: " + nombre + "\n" +
                "| Año de Nacimiento: " + fechaDeNacimiento + "\n" +
                "| Año de Deceso: " + fechaDeDeceso + "\n" +
                "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n";
    }
}
