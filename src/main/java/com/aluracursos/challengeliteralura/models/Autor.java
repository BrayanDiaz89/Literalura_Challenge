package com.aluracursos.challengeliteralura.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
    @ManyToOne
    //@JoinColumn(name = "libro_id")
    private Libro libro;

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

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {

        return  "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n" +
                "|                                      | AUTOR: |\n" +
                "| Nombre: " + nombre + "\n" +
                "| Año de Nacimiento: " + fechaDeNacimiento + "\n" +
                "| Año de Deceso: " + fechaDeDeceso + "\n" +
                "| Libros: " + "| "+ libro.getTitulo() + " |"+"\n" +
                "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n";
    }
}
