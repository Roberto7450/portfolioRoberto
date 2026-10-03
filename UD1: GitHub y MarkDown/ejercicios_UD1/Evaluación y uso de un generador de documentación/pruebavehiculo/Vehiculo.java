/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebavehiculo;

/**
 * Representa un vehículo con su propietario, su kilometraje y un depósito de
 * combustible.
 * <p>
 * Permite viajar (gastando combustible), repostar litros concretos, llenar el
 * depósito y llevar la cuenta del dinero gastado. El precio del combustible es
 * común a todos los vehículos ({@link #actualizarPrecio(double)}).
 * </p>
 * <p>
 * Ejemplo de uso:</p>
 * <pre>{@code
 * Vehiculo coche = new Vehiculo("Seat", "Ibiza", "1234ABC", "Ana", "12345678Z", 0, 40);
 * coche.llenar();
 * System.out.println(coche.viajar(100));
 * }</pre>
 *
 * @author usumaniana
 * @version 1.1
 * @see DNI
 */
public class Vehiculo {

    // TODO: calcular la fecha de la próxima revisión (menos de 4 años: a los 4;
    // menos de 10 años: cada 2; más de 10: cada año) y cuánto falta para ella.
    // Requiere guardar la fecha de matriculación.

    /** Consumo del vehículo, en litros por kilómetro (7 l/100 km). */
    private static final double CONSUMO_POR_KM = 0.07;

    /** Precio del combustible por litro, compartido por todos los vehículos. */
    private static double precio = 1;

    /** Marca del vehículo. No cambia una vez creado. */
    private final String MARCA;

    /** Modelo del vehículo. No cambia una vez creado. */
    private final String MODELO;

    /** Matrícula del vehículo. No cambia una vez creado. */
    private final String MATRICULA;

    /** Nombre del propietario. */
    private String nombre;

    /** DNI del propietario. */
    private DNI dni;

    /** Kilómetros totales recorridos por el vehículo. */
    private double kilometrosRecorridos;

    /** Capacidad máxima del depósito, en litros. */
    private double capacidadDeposito;

    /** Combustible actual en el depósito, en litros. */
    private double cantidadCombustible;

    /** Dinero total gastado en combustible, en euros. */
    private double dineroGastado;

    /**
     * Crea un vehículo con el depósito vacío.
     *
     * @param marca marca del vehículo
     * @param modelo modelo del vehículo
     * @param matricula matrícula del vehículo
     * @param nombre nombre del propietario
     * @param dni DNI del propietario, con letra (por ejemplo
     * {@code "12345678Z"})
     * @param kilometrosRecorridos kilómetros que ya tiene el vehículo (no
     * puede ser negativo)
     * @param capacidadDeposito capacidad del depósito en litros (debe ser
     * mayor que 0)
     * @throws IllegalArgumentException si el DNI no es válido, los kilómetros
     * son negativos o la capacidad no es positiva
     */
    public Vehiculo(String marca, String modelo, String matricula, String nombre,
            String dni, double kilometrosRecorridos, double capacidadDeposito) {
        if (kilometrosRecorridos < 0) {
            throw new IllegalArgumentException("Los kilómetros no pueden ser negativos.");
        }
        if (capacidadDeposito <= 0) {
            throw new IllegalArgumentException("La capacidad del depósito debe ser mayor que 0.");
        }
        this.MARCA = marca;
        this.MODELO = modelo;
        this.MATRICULA = matricula;
        this.nombre = nombre;
        this.dni = new DNI();
        this.dni.establecer(dni); // Lanza IllegalArgumentException si el DNI no es válido
        this.kilometrosRecorridos = kilometrosRecorridos;
        this.capacidadDeposito = capacidadDeposito;
    }

    //GETTERS
    /**
     * Devuelve la marca del vehículo.
     *
     * @return la marca
     */
    public String getMARCA() {
        return MARCA;
    }

    /**
     * Devuelve el modelo del vehículo.
     *
     * @return el modelo
     */
    public String getMODELO() {
        return MODELO;
    }

    /**
     * Devuelve la matrícula del vehículo.
     *
     * @return la matrícula
     */
    public String getMATRICULA() {
        return MATRICULA;
    }

    /**
     * Devuelve el nombre del propietario.
     *
     * @return el nombre del propietario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el DNI del propietario.
     *
     * @return el DNI del propietario
     */
    public DNI getDni() {
        return dni;
    }

    /**
     * Devuelve los kilómetros totales recorridos por el vehículo.
     *
     * @return los kilómetros totales
     */
    public double getNumKilometros() {
        return kilometrosRecorridos;
    }

    /**
     * Devuelve la capacidad del depósito.
     *
     * @return la capacidad, en litros
     */
    public double getCapacidadDeposito() {
        return capacidadDeposito;
    }

    //SETTERS
    /**
     * Cambia el nombre del propietario.
     *
     * @param nombre el nuevo nombre del propietario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Cambia el DNI del propietario.
     *
     * @param dni el nuevo DNI (no puede ser {@code null})
     * @throws IllegalArgumentException si el DNI es {@code null}
     */
    public void setDni(DNI dni) {
        if (dni == null) {
            throw new IllegalArgumentException("El DNI no puede ser nulo.");
        }
        this.dni = dni;
    }

    /**
     * Cambia los kilómetros totales del vehículo.
     *
     * @param numKilometros los nuevos kilómetros totales (no puede ser
     * negativo)
     * @throws IllegalArgumentException si el valor es negativo
     */
    public void setNumKilometros(double numKilometros) {
        if (numKilometros < 0) {
            throw new IllegalArgumentException("Los kilómetros no pueden ser negativos.");
        }
        this.kilometrosRecorridos = numKilometros;
    }

    /**
     * Cambia la capacidad del depósito.
     *
     * @param capacidadDeposito la nueva capacidad en litros (debe ser mayor
     * que 0 y no menor que el combustible que hay ahora en el depósito)
     * @throws IllegalArgumentException si la capacidad no es positiva o es
     * menor que el combustible actual
     */
    public void setCapacidadDeposito(double capacidadDeposito) {
        if (capacidadDeposito <= 0 || capacidadDeposito < cantidadCombustible) {
            throw new IllegalArgumentException(
                    "La capacidad debe ser mayor que 0 y no menor que el combustible actual.");
        }
        this.capacidadDeposito = capacidadDeposito;
    }

    //METODOS DE NEGOCIO
    /**
     * Muestra los datos que identifican al vehículo.
     *
     * @return una cadena con la marca, el modelo y la matrícula
     */
    public String mostrarDatosIdentificativos() {
        return "Marca: " + MARCA + ", Modelo: " + MODELO + ", Matrícula: " + MATRICULA;
    }

    /**
     * Muestra el estado actual del vehículo.
     *
     * @return una cadena con los kilómetros recorridos y el combustible que
     * hay en el depósito
     */
    public String mostrarEstadoVehiculo() {
        return String.format("Kilómetros recorridos: %.2f, Combustible en el depósito: %.2f l",
                kilometrosRecorridos, cantidadCombustible);
    }

    /**
     * Realiza un viaje de los kilómetros indicados. Suma los kilómetros al
     * total y gasta combustible según el consumo del vehículo (0,07 l/km). Si
     * no hay combustible suficiente, el viaje no se realiza.
     *
     * @param kmViajar kilómetros que se quieren recorrer (debe ser mayor que
     * 0)
     * @return un mensaje con el resultado del viaje: kilómetros realizados,
     * combustible restante y kilómetros totales; o un mensaje de error si los
     * kilómetros no son válidos o no hay combustible suficiente
     */
    public String viajar(double kmViajar) {
        if (kmViajar <= 0) {
            return "Los kilómetros a viajar deben ser mayores que 0.";
        }
        double litrosNecesarios = kmViajar * CONSUMO_POR_KM;
        if (litrosNecesarios > cantidadCombustible) {
            return "No hay combustible suficiente.";
        }
        kilometrosRecorridos += kmViajar;
        cantidadCombustible -= litrosNecesarios;
        return String.format("Kilómetros realizados: %.2f, Depósito: %.2f l, Kilómetros totales: %.2f",
                kmViajar, cantidadCombustible, kilometrosRecorridos);
    }

    /**
     * Reposta una cantidad de litros y suma su coste al dinero gastado. No se
     * puede repostar más de lo que cabe en el depósito.
     *
     * @param litros litros a repostar (debe ser mayor que 0)
     * @return un mensaje con los litros repostados, el coste de esta operación
     * y el total gastado; o un mensaje de error si los litros no son válidos o
     * no caben en el depósito
     */
    public String repostar(double litros) {
        if (litros <= 0) {
            return "Los litros a repostar deben ser mayores que 0.";
        }
        if (cantidadCombustible + litros > capacidadDeposito) {
            return "No se pueden repostar tantos litros.";
        }
        double coste = litros * precio;
        cantidadCombustible += litros;
        dineroGastado += coste;
        return String.format("Litros repostados: %.2f, Coste: %.2f €, Total gastado: %.2f €",
                litros, coste, dineroGastado);
    }

    /**
     * Llena el depósito hasta su capacidad máxima y suma el coste de los litros
     * añadidos al dinero gastado.
     *
     * @return un mensaje con el combustible que hay tras llenar, el coste de
     * esta operación y el total gastado
     */
    public String llenar() {
        double litros = capacidadDeposito - cantidadCombustible;
        double coste = litros * precio;
        cantidadCombustible = capacidadDeposito;
        dineroGastado += coste;
        return String.format("Cantidad de combustible: %.2f l, Coste: %.2f €, Total gastado: %.2f €",
                cantidadCombustible, coste, dineroGastado);
    }

    /**
     * Actualiza el precio del litro de combustible. Al ser un método estático,
     * el cambio afecta a todos los vehículos.
     *
     * @param nuevoPrecio el nuevo precio por litro (debe ser mayor que 0)
     * @return un mensaje con el nuevo precio, o un mensaje de error si el
     * precio no es válido
     */
    public static String actualizarPrecio(double nuevoPrecio) {
        if (nuevoPrecio <= 0) {
            return "El precio debe ser mayor que 0.";
        }
        precio = nuevoPrecio;
        return String.format("El nuevo precio del combustible es: %.2f €/l", precio);
    }

    /**
     * Muestra el propietario del vehículo.
     *
     * @return una cadena con el nombre y el NIF del propietario
     */
    public String mostrarPropietario() {
        return "Propietario: " + nombre + ", DNI: " + dni;
    }

}
