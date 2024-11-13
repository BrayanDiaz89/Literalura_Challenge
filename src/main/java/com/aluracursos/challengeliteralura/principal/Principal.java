package com.aluracursos.challengeliteralura.principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class Principal {
private Scanner teclado = new Scanner(System.in);

    @Autowired
    private final MetodosPrincipal metodo;

    public Principal(MetodosPrincipal metodo) {
        this.metodo = metodo;
    }

    public void muestraElMenu(){
        System.out.println("\n|--   ¡Bienvenido a tú catálogo de libros!   --|");
        String menu =
                "\n------|=!=!=| - Menú principal: - |=!=!|------\n"+
                "| Elige una opción para navegar en tú catálogo: \n"+
                "| 1) Buscar libro\n"+
                "| 2) Ver libros de tú base de datos.\n"+
                "| 3) Ver autores de tú base de datos.\n"+
                "| 4) Ver autores según fecha de nacimiento (Fecha desde, fecha hasta).\n"+
                "| 5) Buscar libros por idiomas.\n"+
                "| 6) Listar todos los libros con su recurso electrónico descargable.\n"+
                "| 7) Ver estadísticas de tus libros.\n"+
                "| 8) Salir.";
        String menuCase4 = "\n------|=!=!=| - Sub Menú: - |=!=!|------\n"+
                "| Elige una opción para navegar en tú catálogo: \n"+
                "| 1) Ver autores que nacieron entre fecha inicial y fecha mayor. \n"+
                "| 2) Ver autores que nacieron entre fecha inicial y fallecen entre fecha mayor.\n"+
                "| 3) Volver al menú principal.";

        String menuCase6= "\n------|=!=!=| - Sub Menú: - |=!=!|------\n"+
                "| Elige una opción para navegar en tú catálogo: \n"+
                "| 1) Ver autores que nacieron entre fecha inicial y fecha mayor. \n"+
                "| 2) Ver autores que nacieron entre fecha inicial y fallecen entre fecha mayor.\n"+
                "| 3) Volver al menú principal.";

        int decision = 0;
        while(decision != 8){
            System.out.println(menu);
            try {
                decision = teclado.nextInt();

                switch (decision) {
                    case 1:
                        metodo.buscarLibroWeb();
                        break;
                    case 2:
                        metodo.getLibros();
                        break;
                    case 3:
                        metodo.getAutores();
                        break;
                    case 4:
                        while (true) {
                            System.out.println(menuCase4);
                            try {
                                decision = teclado.nextInt();
                                switch (decision) {
                                    case 1:
                                        metodo.verAutoresConFechaNacimientoRecibida();
                                        break;
                                    case 2:
                                        metodo.verAutoresVivosEntreFechaRecibida();
                                        break;
                                    case 3:
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
                    case 5:
                        metodo.buscarLibrosPorIdioma();
                        break;
                    case 6:
                        metodo.verLibrosConSuRecursoElectronico();
                        break;
                    case 7:
                        metodo.verEstadisticasDeTusLibros();
                        break;
                    case 8:
                        System.out.println("Saliendo del programa...");
                        teclado.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opción no válida, por favor intenta nuevamente.");
                        break;
                }
            }catch (InputMismatchException e){
                System.out.println("Entrada no válida. Por favor, ingresa un valor numérico.");
                teclado.nextLine();
            }
        }
    }

}
