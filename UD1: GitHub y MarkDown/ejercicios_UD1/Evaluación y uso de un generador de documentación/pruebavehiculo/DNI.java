/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebavehiculo;

/**
 * Representa un DNI/NIF español: un número de 7 u 8 cifras más una letra de
 * control.
 * <p>
 * La letra se calcula como el carácter que ocupa la posición
 * {@code número % 23} en la cadena {@code "TRWAGMYFPDXBNJZSQVHLCKE"}.
 * </p>
 * <p>
 * Ejemplo de uso:</p>
 * <pre>{@code
 * DNI dni = new DNI();
 * dni.establecer("12345678Z");
 * System.out.println(dni.obtenerDNI()); // 12345678
 * System.out.println(dni.obtenerNIF()); // 12345678Z
 * }</pre>
 *
 * @author usumaniana
 * @version 1.1
 */
public class DNI {

    //ATRIBUTOS
    /** Cadena con las letras de control, indexadas por el resto de dividir entre 23. */
    private static final String LETRAS = "TRWAGMYFPDXBNJZSQVHLCKE";

    /** Menor número de DNI admitido (7 cifras). */
    private static final int MIN_DNI = 1_000_000;

    /** Mayor número de DNI admitido (8 cifras). */
    private static final int MAX_DNI = 99_999_999;

    /** Número del DNI, sin letra. Vale 0 mientras no se haya establecido. */
    private int dni;

    //CONSTRUCTOR
    /**
     * Crea un DNI vacío (valor 0). Hay que darle valor después con
     * {@link #establecer(int)} o {@link #establecer(String)}.
     */
    public DNI() {
    }

    //METODOS PUBLICOS
    /**
     * Devuelve el número del DNI, sin la letra.
     *
     * @return el número del DNI, o 0 si todavía no se ha establecido ninguno
     */
    public int obtenerDNI() {
        return dni;
    }

    /**
     * Devuelve el NIF completo: el número del DNI seguido de su letra de
     * control.
     *
     * @return el NIF completo, por ejemplo {@code "12345678Z"}
     */
    public String obtenerNIF() {
        //Cálculo de la letra del NIF
        char letraNIF = DNI.calcularLetraNIF(dni);
        //Construcción de la cadena del NIF: número + letra
        return Integer.toString(dni) + letraNIF;
    }

    /**
     * Establece el DNI a partir de un NIF completo (número + letra). Se
     * comprueba que la letra sea la correcta para ese número; las minúsculas
     * se aceptan.
     *
     * @param nif el NIF completo, por ejemplo {@code "12345678Z"}
     * @throws IllegalArgumentException si el NIF es nulo, no tiene el formato
     * correcto o la letra no coincide con el número
     */
    public void establecer(String nif) {
        if (validarNIF(nif)) { //Valor válido: lo almacenamos
            this.dni = DNI.extraerNumeroNIF(nif);
        } else { // Valor inválido: lanzamos una excepción
            throw new IllegalArgumentException("NIF inválido: " + nif);
        }
    }

    /**
     * Establece el DNI a partir de su número (sin letra).
     *
     * @param dni el número del DNI, entre 1.000.000 y 99.999.999 (ambos
     * incluidos)
     * @throws IllegalArgumentException si el número está fuera de ese rango
     */
    public void establecer(int dni) {
        //Comprobación de rangos
        if (esNumeroValido(dni)) {
            this.dni = dni; //Valor válido: lo almacenamos
        } else { // Valor inválido: lanzamos una excepción
            throw new IllegalArgumentException("DNI inválido: " + dni);
        }
    }

    /**
     * Devuelve el NIF completo como texto, de modo que el objeto se pueda
     * imprimir directamente.
     *
     * @return el mismo valor que {@link #obtenerNIF()}
     */
    @Override
    public String toString() {
        return obtenerNIF();
    }

    //METODOS PRIVADOS
    /**
     * Comprueba que un número esté dentro del rango permitido para un DNI.
     *
     * @param numero número a comprobar
     * @return {@code true} si está entre 1.000.000 y 99.999.999
     */
    private static boolean esNumeroValido(int numero) {
        return numero >= MIN_DNI && numero <= MAX_DNI;
    }

    /**
     * Calcula la letra de control correspondiente a un número de DNI.
     *
     * @param dni número del DNI
     * @return la letra de control
     */
    private static char calcularLetraNIF(int dni) {
        return LETRAS.charAt(dni % 23);
    }

    /**
     * Comprueba si un NIF es válido: longitud correcta (8 o 9 caracteres),
     * parte numérica válida y letra de control correcta.
     *
     * @param nif NIF a validar (puede ser {@code null})
     * @return {@code true} si el NIF es válido, {@code false} en caso contrario
     */
    private static boolean validarNIF(String nif) {
        // El parámetro debe ser un objeto no nulo de entre 8 (7+1) y 9 (8+1) caracteres
        if (nif == null || nif.length() < 8 || nif.length() > 9) {
            return false;
        }
        try {
            char letraLeida = Character.toUpperCase(DNI.extraerLetraNIF(nif));
            int numeroLeido = DNI.extraerNumeroNIF(nif);
            // El número debe estar en rango y la letra debe coincidir con la calculada
            return esNumeroValido(numeroLeido)
                    && letraLeida == DNI.calcularLetraNIF(numeroLeido);
        } catch (NumberFormatException e) {
            // La parte numérica contiene caracteres que no son dígitos
            return false;
        }
    }

    /**
     * Extrae la letra (último carácter) de un NIF.
     *
     * @param nif NIF completo
     * @return la letra del NIF
     */
    private static char extraerLetraNIF(String nif) {
        return nif.charAt(nif.length() - 1);
    }

    /**
     * Extrae la parte numérica (todo menos el último carácter) de un NIF.
     *
     * @param nif NIF completo
     * @return el número del NIF
     * @throws NumberFormatException si la parte numérica no es un entero
     */
    private static int extraerNumeroNIF(String nif) {
        return Integer.parseInt(nif.substring(0, nif.length() - 1));
    }

}
