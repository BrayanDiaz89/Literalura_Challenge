package com.aluracursos.challengeliteralura.repository;

import com.aluracursos.challengeliteralura.models.Autor;
import com.aluracursos.challengeliteralura.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

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

}
