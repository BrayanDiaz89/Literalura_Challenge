package com.aluracursos.challengeliteralura.principal;

import com.aluracursos.challengeliteralura.models.DatosAutor;
import com.aluracursos.challengeliteralura.models.DatosLibro;
import com.aluracursos.challengeliteralura.models.Libro;
import com.aluracursos.challengeliteralura.models.ResultadoAPI;
import com.aluracursos.challengeliteralura.repository.LibroRepository;
import com.aluracursos.challengeliteralura.service.ConsumoAPI;
import com.aluracursos.challengeliteralura.service.ConvierteDatos;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class MetodosPrincipal {
    private Scanner teclado = new Scanner(System.in);
    private Integer idLibro;
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosLibro> datosLibros = new ArrayList<>();
    private Optional<Libro> libroBuscado;
    private List<Libro> libros;
    private final LibroRepository repository;

    public MetodosPrincipal(LibroRepository repository) {
        this.repository = repository;
    }

    public void getDatosLibro() {
        System.out.println("Escribe el id del libro que deseas buscar: ");
        idLibro = teclado.nextInt();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?ids=" + idLibro);
        System.out.println("JSON recibido: " + json);

        // Cambia el tipo de deserialización a ResultadoAPI
        ResultadoAPI resultado = conversor.obtenerDatos(json, ResultadoAPI.class);
        if (resultado.libros() != null && !resultado.libros().isEmpty()) {
            DatosLibro datos = resultado.libros().get(0); // Toma el primer libro
            System.out.println("Datos deserializados: " + datos);
        } else {
            System.out.println("No se encontraron datos de libros en el JSON.");
        }
    }

}
