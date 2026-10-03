/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.pruebavehiculo;

import java.util.Scanner;

/**
 * Programa de consola para probar la clase {@link Vehiculo}.
 * <p>
 * Muestra un menú con el que se puede crear un vehículo, consultar sus datos,
 * viajar, repostar, llenar el depósito, cambiar el precio del combustible y ver
 * el propietario.
 * </p>
 *
 * @author usumaniana
 * @version 1.1
 * @see Vehiculo
 * @see DNI
 */
public class PruebaVehiculo {

    /**
     * Constructor privado: esta clase solo se usa para arrancar el programa
     * con {@link #main(String[])} y no hace falta crear objetos de ella.
     */
    private PruebaVehiculo() {
    }

    /**
     * Punto de entrada del programa. Muestra el menú en bucle hasta que el
     * usuario elige la opción 9 (salir).
     *
     * @param args argumentos de la línea de comandos (no se usan)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Vehiculo vehiculoActual = null;
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero(sc, "Selecciona una opción: ");

            // Todas las opciones salvo crear (1), precio (7) y salir (9) necesitan un vehículo
            if (opcion >= 2 && opcion <= 8 && opcion != 7 && vehiculoActual == null) {
                System.out.println("Error: no hay ningún vehículo. Crea uno primero con la opción 1.");
                continue;
            }

            switch (opcion) {
                case 1:
                    Vehiculo nuevo = crearVehiculo(sc);
                    if (nuevo != null) { // Si hubo error, se conserva el vehículo anterior
                        vehiculoActual = nuevo;
                    }
                    break;
                case 2:
                    System.out.println(vehiculoActual.mostrarDatosIdentificativos());
                    break;
                case 3:
                    System.out.println(vehiculoActual.mostrarEstadoVehiculo());
                    break;
                case 4:
                    double kilometros = leerDecimal(sc, "Introduce los kilómetros a viajar: ");
                    System.out.println(vehiculoActual.viajar(kilometros));
                    break;
                case 5:
                    double litros = leerDecimal(sc, "Introduce los litros a repostar: ");
                    System.out.println(vehiculoActual.repostar(litros));
                    break;
                case 6:
                    System.out.println(vehiculoActual.llenar());
                    break;
                case 7:
                    double precio = leerDecimal(sc, "Introduce el nuevo precio del combustible: ");
                    System.out.println(Vehiculo.actualizarPrecio(precio));
                    break;
                case 8:
                    System.out.println(vehiculoActual.mostrarPropietario());
                    break;
                case 9:
                    System.out.println("Has salido del programa.");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 9);

        sc.close();
    }

    /**
     * Imprime el menú de opciones por pantalla.
     */
    private static void mostrarMenu() {
        System.out.println();
        System.out.println("MENÚ");
        System.out.println("1. Nuevo Vehículo");
        System.out.println("2. Ver datos identificativos");
        System.out.println("3. Ver estado del vehículo");
        System.out.println("4. Viajar");
        System.out.println("5. Repostar");
        System.out.println("6. Llenar depósito");
        System.out.println("7. Actualizar precio combustible");
        System.out.println("8. Mostrar propietario");
        System.out.println("9. Salir");
    }

    /**
     * Pide por teclado los datos de un vehículo y lo crea.
     *
     * @param sc el {@code Scanner} con el que se lee la entrada del usuario
     * @return el vehículo creado, o {@code null} si los datos no eran válidos
     * (por ejemplo, un DNI incorrecto)
     */
    private static Vehiculo crearVehiculo(Scanner sc) {
        System.out.print("Marca: ");
        String marca = sc.nextLine();
        System.out.print("Modelo: ");
        String modelo = sc.nextLine();
        System.out.print("Matrícula: ");
        String matricula = sc.nextLine();
        System.out.print("Nombre del propietario: ");
        String nombre = sc.nextLine();
        System.out.print("DNI del propietario (con letra): ");
        String dni = sc.nextLine();
        double km = leerDecimal(sc, "Kilómetros recorridos: ");
        double capacidad = leerDecimal(sc, "Capacidad del depósito (litros): ");

        try {
            Vehiculo vehiculo = new Vehiculo(marca, modelo, matricula, nombre, dni, km, capacidad);
            System.out.println("Vehículo creado con éxito.");
            return vehiculo;
        } catch (IllegalArgumentException e) {
            System.out.println("No se pudo crear el vehículo: " + e.getMessage());
            return null;
        }
    }

    /**
     * Lee un número entero por teclado. Repite la pregunta hasta que el usuario
     * escribe un valor válido.
     *
     * @param sc el {@code Scanner} con el que se lee la entrada
     * @param mensaje el texto que se muestra antes de leer
     * @return el número entero introducido
     */
    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número entero válido.");
            }
        }
    }

    /**
     * Lee un número decimal por teclado (acepta coma o punto como separador).
     * Repite la pregunta hasta que el usuario escribe un valor válido.
     *
     * @param sc el {@code Scanner} con el que se lee la entrada
     * @param mensaje el texto que se muestra antes de leer
     * @return el número decimal introducido
     */
    private static double leerDecimal(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número válido.");
            }
        }
    }
}
