package com.aluracursos.challengeliteralura.repository;

import com.aluracursos.challengeliteralura.models.Autor;
import com.aluracursos.challengeliteralura.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    //Buscar libro por nombre
    @Query("SELECT l FROM Libro l JOIN FETCH l.autores WHERE l.titulo = :titulo")
    Optional<Libro> findByTitulo(@Param("titulo") String titulo);

    //Buscar libro por id
    @Query("SELECT l FROM Libro l JOIN FETCH l.autores WHERE l.id = :id")
    Optional<Libro> findByIdWithAutores(@Param("id")Long id);

    //Ver todos los libros
    @Query("SELECT l FROM Libro l JOIN FETCH l.autores")
    List<Libro> findAllWithAutores();

    //Ver autores
    @Query("SELECT a FROM Autor a JOIN FETCH a.libros")
    List<Autor> findAllWithLibros();

    //Obtengo la lista de autores con fecha de nacimiento entre Fecha inicio y fecha fin digitados por el usuario
    @Query("SELECT a FROM Autor a JOIN FETCH a.libros WHERE a.fechaDeNacimiento BETWEEN :fechaInicio AND :fechaFin")
    List<Autor> findAllByFechaDeVida(@Param("fechaInicio") Integer fechaInicio, @Param("fechaFin") Integer fechaFin);

    //Obtengo la lista de libros según el lenguaje recibido por el usuario.
    @Query("SELECT l FROM Libro l JOIN FETCH l.autores WHERE l.lenguaje = :lenguaje")
    List<Libro> findAllByLenguajeIngresado(@Param("lenguaje") String lenguaje);

    //Obtengo la lista de mi columna lenguaje de mi tabla Libro, sin tener valores repetidos DISTINCT
    @Query("SELECT DISTINCT l.lenguaje FROM Libro l")
    List<String> findDistinctListadoDeLenguajes();

}
