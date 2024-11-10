package com.aluracursos.challengeliteralura.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)//Columna unica porque no quiero libros repetidos (con el mismo nombre)
    private String titulo;
    private List<String> lenguaje;
    private String poster;
    private String libroElectronico;
    private Double numeroDeDescargas;
    //orphanRemoval = true, asegurará que si eliminamos un autor de la tabla autores, si este autor no tiene relación con ningun otro libro
    //se eliminará totalmente de la bd, para que no queden datos huerfanos.
    @ManyToMany(mappedBy = "libros", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores = new ArrayList<>();

    //Metodo que permite agregar un autor a la base de datos, al mismo tiempo que se agrega su libro.
    public void agregarAutor(Autor autor){
        if (!this.autores.contains(autor)) {
            this.autores.add(autor);
            autor.getLibros().add(this);
        }
    }

    //JPA nos exige tener un constructor predeterminado con el mismo nombre del personalizado, ya él internamente
    //reconoce el personalizado que tenemos después.
    public Libro(){} //Constructor predeterminado.


    //Constructor personalizado.
    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        //this.lenguaje = String.valueOf(datosLibro.lenguaje());
        this.lenguaje = datosLibro.lenguaje();
        this.poster = String.valueOf(datosLibro.formats().poster());
        this.libroElectronico = String.valueOf(datosLibro.formats().libroElectronico());
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(List<String> lenguaje) {
        this.lenguaje = lenguaje;
    }

    public String getPoster() {
        return poster;
    }
    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getLibroElectronico() {
        return libroElectronico;
    }

    public void setLibroElectronico(String libroElectronico) {
        this.libroElectronico = libroElectronico;
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
        String nombresAutores = autores.stream()
                .map(Autor::getNombre)
                .collect(Collectors.joining(", "));

        return  "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n" +
                "|                                      | LIBRO: |\n" +
                "| Título: " + titulo + "\n" +
                "| Lenguaje: " + lenguaje + "\n" +
                "| Poster: " + poster + "\n" +
                "| Número de descargas: " + numeroDeDescargas + "\n" +
                "| Autor(es): " + nombresAutores + "\n" +
                "| Libro electrónico (.zip): " + libroElectronico + "\n" +
                "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n";
    }
}
