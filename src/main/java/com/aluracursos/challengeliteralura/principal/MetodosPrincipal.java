package com.aluracursos.challengeliteralura.principal;

import com.aluracursos.challengeliteralura.models.*;
import com.aluracursos.challengeliteralura.repository.LibroRepository;
import com.aluracursos.challengeliteralura.service.ConsumoAPI;
import com.aluracursos.challengeliteralura.service.ConvierteDatos;
import org.springframework.stereotype.Component;

import java.util.*;


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
        // Crear un mapa para almacenar los datos del autor como clave y su lista de libros como valor.
        Map<String, List<String>> autorLibrosMap = new LinkedHashMap<>();

        // Paso 1: Recorrer la lista de autores y agrupar los libros por cada autor
        for (Autor autor : autores) {
            // Crear una clave única para cada autor usando su nombre, fecha de nacimiento y fecha de deceso
            // Esto nos permite identificar a cada autor de manera única y evita duplicados
            String datosAutor = "| Nombre: " + autor.getNombre() + "\n" +
                    "| Año de Nacimiento: " + autor.getFechaDeNacimiento() + "\n" +
                    "| Año de Deceso: " + autor.getFechaDeDeceso();

            // Si el autor aún no está en el mapa, se agrega con una lista vacía para sus libros
            autorLibrosMap.putIfAbsent(datosAutor, new ArrayList<>());

            // Recorrer la lista de libros del autor y agregarlos a la lista del mapa
            for (Libro libro : autor.getLibros()) {
                // Añadir el título del libro a la lista del autor en el mapa, solo si aún no está
                if (!autorLibrosMap.get(datosAutor).contains(libro.getTitulo())) {
                    autorLibrosMap.get(datosAutor).add(libro.getTitulo());
                }
            }
        }

        // Paso 2: Imprimir cada autor y su lista de libros a partir del mapa
        for (Map.Entry<String, List<String>> entry : autorLibrosMap.entrySet()) {
            String datosAutor = entry.getKey(); // Obtener la clave (información del autor)
            List<String> libros = entry.getValue(); // Obtener el valor (lista de libros)

            // Imprimir la información del autor en el formato especificado
            System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            System.out.println("|                                      | AUTOR: |");
            System.out.println(datosAutor);
            System.out.println("| Libros: -- " + String.join(" -- ", libros) + " --");
            System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n");
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

    public void verAutoresVivosDesdeFechaRecibida() {
        try {
            System.out.println("Digite apartir de que fecha de nacimiento deseas ver los autores: ");
            Integer fechaInicio = teclado.nextInt();
            System.out.println("Digite hasta que fecha de deceso deseas ver los autores: ");
            Integer fechaFin = teclado.nextInt();
            List<Autor> autores = repository.findAllByFechaDeVida(fechaInicio, fechaFin);

            if(autores.isEmpty()){
                System.out.println("No hay registros de autores según las fechas de nacimiento recibidas.");
            }
            verAutoresYSusLibros(autores);
        }catch (InputMismatchException e){
            System.out.println("Entrada no válida. Por favor, ingresa un valor numérico.");
            teclado.nextLine();
        }
    }

    public void buscarLibrosPorIdioma() {
        Libro libro = new Libro();
        List<String> lenguajes = new ArrayList<>();
        System.out.println("Estos son los idiomas presentes en tú base de datos: ");
        lenguajes.forEach(System.out::println);
        System.out.println("Digita el lenguaje de los libros que deseas consultar: ");
        String lenguajeUsuario = teclado.nextLine();
        System.out.println(lenguajes);

    }
}
