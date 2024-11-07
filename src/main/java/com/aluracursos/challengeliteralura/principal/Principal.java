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
                "| 1) Ver libro\n"+
                "| 2) Salir.";
        int decision = 0;
        while(decision != 2){
            System.out.println(menu);
            try {
                decision = teclado.nextInt();

                switch (decision) {
                    case 1:
                        metodo.buscarLibroWeb();
                        break;
                    case 2:
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
