package com.aluracursos.challengeliteralura.principal;

import com.aluracursos.challengeliteralura.models.*;
import com.aluracursos.challengeliteralura.repository.LibroRepository;
import com.aluracursos.challengeliteralura.service.ConsumoAPI;
import com.aluracursos.challengeliteralura.service.ConvierteDatos;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class MetodosPrincipal {
    private Scanner teclado = new Scanner(System.in);
    private String nameLibro;
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private Optional<Libro> libroBuscado;
    private List<Libro> libros;
    private final LibroRepository repository;

    public MetodosPrincipal(LibroRepository repository) {
        this.repository = repository;
    }

    //Metodo para testear la busqueda en mi api por id y visualizar mis datos deserializados.
    public DatosLibro getDatosLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar: ");
        nameLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nameLibro.replace(" ", "+"));
        //System.out.println("JSON recibido: " + json);

        // Cambia el tipo de deserialización a ResultadoAPI
        ResultadoAPI resultado = conversor.obtenerDatos(json, ResultadoAPI.class);
        if (resultado.libros() != null && !resultado.libros().isEmpty()) {
            DatosLibro datos = resultado.libros().get(0); // Toma el primer libro
            //System.out.println("Datos deserializados: " + datos);
            return datos;
        } else {
            //System.out.println("No se encontraron datos de libros en el JSON.");
            return null;
        }
    }

    public void verAutoresYSusLibros(List<Autor> autores) {
        Map<String, List<String>> autorLibrosMap = new LinkedHashMap<>();

        // Iterar sobre la lista de autores para agrupar los libros por autor
        for (Autor autor : autores) {
            // Crear una clave única para cada autor usando nombre, fecha de nacimiento y deceso
            String datosAutor = "| Nombre: " + autor.getNombre() + "\n" +
                    "| Año de Nacimiento: " + autor.getFechaDeNacimiento() + "\n" +
                    "| Año de Deceso: " + autor.getFechaDeDeceso();

            // Inicializar la lista de libros si es la primera vez que encontramos al autor
            autorLibrosMap.putIfAbsent(datosAutor, new ArrayList<>());

            // Agregar cada título de libro único para el autor
            for (Libro libro : autor.getLibros()) {
                if (!autorLibrosMap.get(datosAutor).contains(libro.getTitulo())) {
                    autorLibrosMap.get(datosAutor).add(libro.getTitulo());
                }
            }
        }

        // Imprimir la información de los autores y sus libros una vez completado el mapa
        for (Map.Entry<String, List<String>> entry : autorLibrosMap.entrySet()) {
            String datosAutor = entry.getKey();
            List<String> libros = entry.getValue();

            System.out.println("\n|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            System.out.println("|                                      | AUTOR: |");
            System.out.println(datosAutor);
            System.out.println("| Libros: -- " + String.join(" -- ", libros) + " --");
            System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        }
    }

    //Metodo para consultar en la base de datos y agregar libro de la api, si no existe.
    public void buscarLibroWeb() {
            DatosLibro datos = getDatosLibro();
            if (datos == null) {
                System.out.println("El libro buscado no fue encontrado.");
                return;
            }
            Optional<Libro> libroExistente = repository.findByTitulo(datos.titulo());
            if (libroExistente.isPresent()) {
                System.out.println("Libro en nuestra base de datos.");
                System.out.println(libroExistente.get());
            } else {

                Libro libro = new Libro(datos);
                for (DatosAutor datosAutor : datos.autores()) {
                    Autor autor = new Autor();
                    autor.setNombre(datosAutor.nombre());
                    autor.setFechaDeNacimiento(datosAutor.anioNacimiento());
                    autor.setFechaDeDeceso(datosAutor.anioDeceso());

                    libro.agregarAutor(autor);
                }

                repository.save(libro);
                System.out.println("Libro guardado con éxito.");
                Optional<Libro> libroGuardado = repository.findByIdWithAutores(libro.getId());
                libroGuardado.ifPresent(System.out::println);
            }
    }
    public void getLibros(){
        libros = repository.findAllWithAutores();
        if  (libros.isEmpty()) {
            System.out.println("No hay libros en tú base de datos.");
        }
        libros.forEach(System.out::println);
    }

    public void getAutores(){
        List<Autor> autores = repository.findAllWithLibros();
        if(autores.isEmpty()){
            System.out.println("No hay autores en tú base de datos.");
        }
        verAutoresYSusLibros(autores);
    }
}
