package com.aluracursos.challengeliteralura.principal;

import com.aluracursos.challengeliteralura.models.*;
import com.aluracursos.challengeliteralura.repository.LibroRepository;
import com.aluracursos.challengeliteralura.service.ConsumoAPI;
import com.aluracursos.challengeliteralura.service.ConvierteDatos;
import org.springframework.data.domain.PageRequest;
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
    private List<Libro> libros = new ArrayList<>();
    private Set<String> lenguajes;
    private final LibroRepository repository;
    private Integer fechaInicio;
    private Integer fechaFin;

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
            for (Libro libro : libros) {
                String primerLenguaje = libro.getLenguaje().isEmpty() ? "" : libro.getLenguaje();
                lenguajes.add(primerLenguaje);
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

    public void verAutoresConFechaNacimientoRecibida() {
        try {
            System.out.println("Digite apartir de que fecha de nacimiento deseas ver los autores: ");
            fechaInicio = teclado.nextInt();
            System.out.println("Digite el límite de fecha de nacimiento de los autores que deseas ver: ");
            fechaFin = teclado.nextInt();
            List<Autor> autores = repository.findAllByFechaDeNacimiento(fechaInicio, fechaFin);

            if(autores.isEmpty()){
                System.out.println("No hay registros que coincidan con las fechas recibidas.");
            }
            verAutoresYSusLibros(autores);
        }catch (InputMismatchException e){
            System.out.println("Entrada no válida. Por favor, ingresa un valor numérico.");
            teclado.nextLine();
        }
    }

    public void verAutoresVivosEntreFechaRecibida() {
        try {
            System.out.println("Digite apartir de que fecha de nacimiento deseas ver los autores: ");
            fechaInicio = teclado.nextInt();
            System.out.println("Digite hasta que fecha de deceso deseas ver los autores: ");
            fechaFin = teclado.nextInt();
            List<Autor> autores = repository.findAllByFechaDeVida(fechaInicio, fechaFin);

            if(autores.isEmpty()){
                System.out.println("No hay registros que coincidan con las fechas recibidas.");
            }
            verAutoresYSusLibros(autores);
        }catch (InputMismatchException e){
            System.out.println("Entrada no válida. Por favor, ingresa un valor numérico.");
            teclado.nextLine();
        }
    }

    public void buscarLibrosPorIdioma() {

        List<String> listaDeLenguajes = repository.findDistinctListadoDeLenguajes();
        System.out.println("Lenguajes disponibles en tus libros: ");
        for(String lenguaje : listaDeLenguajes){
            int i = 1 ;
            System.out.println("| "+ i + ") "  + lenguaje.replaceAll("[\\[\\]]", " "));
        }
        System.out.println("Digita el lenguaje de los libros que deseas consultar: ");
        String lenguajeUsuario = teclado.nextLine();
        String lenguajeConvertido = "["+lenguajeUsuario+"]";

        List<Libro> librosConlenguajeConsultado = repository.findAllByLenguajeIngresado(lenguajeConvertido);
        librosConlenguajeConsultado.forEach(System.out::println);
    }

    public void verLibrosConSuRecursoElectronico() {
        List<LibroAutorDTO> librosElectronicos = repository.findLibrosElectronicosConAutores();
        if(librosElectronicos.isEmpty()){
            System.out.println("No hay registros en la base de datos actualmente. Busca libros en el menú principal :).");
        } else {
            System.out.println("Los libros electrónicos disponibles en tú base de datos son: ");
            librosElectronicos.forEach(l -> System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n" +
                    "|                                      | LIBRO: |\n" +
                    "| Título: " + l.titulo() + "\n" +
                    "| Recurso descargable: " + l.libroElectronico() + "\n" +
                    "| Autor: " + l.nombreAutor() + "\n" +
                    "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n"));
        }
    }

    public void verTop3LibrosMasDescargadosDeAutor(String nombreAutor) {
        List<Libro> librosTop3DescargasAutor = repository.findTop3ByAutorOrderByNumeroDeDescargasDesc(nombreAutor, PageRequest.of(0, 3));//Asigno la cantidad límite de datos que requiero
        librosTop3DescargasAutor.forEach(l-> System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n" +
                "|                                      | LIBRO: |\n" +
                "| Título: " + l.getTitulo() + "\n" +
                "| Número de descargas: " + l.getNumeroDeDescargas() + "\n" +
                "| Recurso electrónico: " + l.getLibroElectronico() + "\n" +
                "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n"));
    }

    private void mostrarSubMenu(List<Libro> librosDelAutor, DoubleSummaryStatistics est, String nameAutor) {
        String subMenu = "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n" +
                "|                                        | Sub Menú: | \n" +
                "1) ¿Deseas ver todas las estadísticas?" + "\n" +
                "2) ¿Deseas ver la media de descargas en sus libros?" + "\n" +
                "3) ¿Deseas ver su libro con más descargas?" + "\n" +
                "4) ¿Deseas ver su libro con menos descargas?" + "\n" +
                "5) Ver top 3 libros más descargados del autor" + "\n" +
                "6) Regresar al menú principal." + "\n";

        int decision = 0;
        while (decision != 6) {
            String todasLasEstadisticas = "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n" +
                    "|                                 | ESTADÍSTICAS COMPLETAS: | \n" +
                    "| Total de libros en su autoría: " + est.getCount() + "\n" +
                    "| Media de descargas de sus libros: " + est.getAverage() + "\n" +
                    "| Libro más descargado de su autoría: " + est.getMax() + "\n" +
                    "| Libro menos descargado de su autoría: " + est.getMin() + "\n" +
                    "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n";
            try {
                System.out.println(subMenu);
                decision = teclado.nextInt();
                teclado.nextLine();  // Limpia el salto de línea después de `nextInt`
                switch (decision) {
                    case 1:
                        System.out.println(todasLasEstadisticas);
                        break;
                    case 2:
                        System.out.println("\n | Media de descargas de sus libros: " + est.getAverage());
                        break;
                    case 3:
                        System.out.println("\n | Número de descargas del libro más descargado en su autoría: " + est.getMax());
                        break;
                    case 4:
                        System.out.println("\n | Número de descargas del libro menos descargado en su autoría: " + est.getMin());
                        break;
                    case 5:
                        verTop3LibrosMasDescargadosDeAutor(nameAutor);
                        break;
                    case 6:
                        System.out.println("Regresando al menú principal...");
                        break;
                    default:
                        System.out.println("Opción no válida, por favor intenta nuevamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingresa un valor numérico.");
                teclado.nextLine();
            }
        }
    }

    public void verEstadisticasDeTusLibrosSegunAutor() {

        System.out.println("Digita el nombre del autor de los libros que deseas ver sus estadísticas: ");
        String nameAutor = teclado.nextLine();

        List<Libro> librosDelAutor = repository.findByNombre(nameAutor);

        if (librosDelAutor.isEmpty()) {
            System.out.println("\nEl autor ingresado, no se encuentra en la base de datos.");
        } else {
            librosDelAutor.forEach(l -> System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n" +
                    "|                                      | LIBROS DE SU AUTORÍA: |\n" +
                    "| Autor: " + nameAutor +"\n" +
                    "| Título: " + l.getTitulo() + "\n" +
                    "| Número de descargas: " + l.getNumeroDeDescargas() + "\n" +
                    "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n"));

            DoubleSummaryStatistics est = librosDelAutor.stream()
                    .filter(l -> l.getNumeroDeDescargas() > 0.0)
                    .collect(Collectors.summarizingDouble(Libro::getNumeroDeDescargas));
            mostrarSubMenu(librosDelAutor, est, nameAutor);
        }
    }

    public void verTop10LibrosMasDescargadosBd() {
        List<Libro> librosTop10Descargas = repository.findTop10ByOrderByNumeroDeDescargasDesc();
        librosTop10Descargas.forEach(l-> System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n" +
                "|                                      | LIBRO: |\n" +
                "| Título: " + l.getTitulo() + "\n" +
                "| Número de descargas: " + l.getNumeroDeDescargas() + "\n" +
                "| Recurso electrónico: " + l.getLibroElectronico() + "\n" +
                "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n"));
    }

}
