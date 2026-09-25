package com.example.hormigacharlie.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * SIMULADOR DE CHARLIE
 *
 * Esta clase contiene solamente lógica Java.
 * No es un Controller ni una Entity.
 *
 * REGLAS DEL ENUNCIADO:
 *
 * 1) Si Charlie encuentra MIGA:
 *    - deja FEROMONA,
 *    - gira 90 grados a la DERECHA,
 *    - avanza una celda.
 *
 * 2) Si Charlie encuentra FEROMONA:
 *    - deja MIGA,
 *    - gira 90 grados a la IZQUIERDA,
 *    - avanza una celda.
 *
 * La miga se representa como celda blanca.
 * La feromona se representa como celda negra.
 */
public class SimuladorCharlie {

    /*
     * Guardamos únicamente las posiciones negras.
     * Una posición que NO está en este Set se considera blanca (miga).
     *
     * Cada posición se guarda como texto "fila,columna".
     */
    private final Set<String> feromonas = new HashSet<>();

    private int fila;
    private int columna;
    private String direccion;

    /*
     * Límites de la zona que ve el turista.
     * Inicialmente corresponden a la dimensión ingresada.
     */
    private int minFila;
    private int maxFila;
    private int minColumna;
    private int maxColumna;

    private final int aumentoVision;

    /*
     * Constructor.
     * Recibe toda la información necesaria para comenzar la simulación.
     */
    public SimuladorCharlie(int filas,
                            int columnas,
                            int filaInicial,
                            int columnaInicial,
                            String direccionInicial,
                            int aumentoVision) {

        this.fila = filaInicial;
        this.columna = columnaInicial;
        this.direccion = direccionInicial.toUpperCase();
        this.aumentoVision = aumentoVision;

        // La matriz inicial empieza en 0,0.
        this.minFila = 0;
        this.minColumna = 0;
        this.maxFila = filas - 1;
        this.maxColumna = columnas - 1;
    }

    /*
     * Ejecuta N movimientos de Charlie.
     */
    public ResultadoSimulacion simular(int cantidadMovimientos) {

        for (int i = 0; i < cantidadMovimientos; i++) {
            moverUnaVez();
        }

        return construirResultado(cantidadMovimientos);
    }

    /*
     * Aplica exactamente UNA iteración de las reglas.
     */
    private void moverUnaVez() {

        String posicionActual = clave(fila, columna);

        /*
         * Si la posición está en feromonas significa que es negra.
         */
        if (feromonas.contains(posicionActual)) {

            // Había feromona: Charlie deja una miga.
            // Por eso quitamos la posición del conjunto de negras.
            feromonas.remove(posicionActual);

            // Luego gira a la izquierda.
            girarIzquierda();

        } else {

            // Había miga: Charlie deja una feromona.
            feromonas.add(posicionActual);

            // Luego gira a la derecha.
            girarDerecha();
        }

        // Finalmente avanza exactamente una celda.
        avanzar();

        // Si salió del terreno visible, ampliamos la zona.
        ampliarSiEsNecesario();
    }

    /*
     * Giro de 90 grados a la derecha.
     *
     * U = arriba
     * R = derecha
     * D = abajo
     * L = izquierda
     */
    private void girarDerecha() {

        switch (direccion) {
            case "U" -> direccion = "R";
            case "R" -> direccion = "D";
            case "D" -> direccion = "L";
            case "L" -> direccion = "U";
        }
    }

    /*
     * Giro de 90 grados a la izquierda.
     */
    private void girarIzquierda() {

        switch (direccion) {
            case "U" -> direccion = "L";
            case "L" -> direccion = "D";
            case "D" -> direccion = "R";
            case "R" -> direccion = "U";
        }
    }

    /*
     * Avance de una celda según la dirección actual.
     */
    private void avanzar() {

        switch (direccion) {
            case "U" -> fila--;
            case "D" -> fila++;
            case "L" -> columna--;
            case "R" -> columna++;
        }
    }

    /*
     * El enunciado indica que cuando Charlie alcanza/supera los límites,
     * el terreno visible debe crecer.
     *
     * Ejemplo:
     * dimensión 5 y aumento 4 -> puede pasar a dimensión 9.
     */
    private void ampliarSiEsNecesario() {

        while (fila < minFila) {
            minFila -= aumentoVision;
        }

        while (fila > maxFila) {
            maxFila += aumentoVision;
        }

        while (columna < minColumna) {
            minColumna -= aumentoVision;
        }

        while (columna > maxColumna) {
            maxColumna += aumentoVision;
        }
    }

    /*
     * Convierte el estado interno en una matriz que Thymeleaf puede recorrer.
     */
    private ResultadoSimulacion construirResultado(int movimientos) {

        List<List<CeldaVista>> matriz = new ArrayList<>();

        for (int f = minFila; f <= maxFila; f++) {

            List<CeldaVista> filaVista = new ArrayList<>();

            for (int c = minColumna; c <= maxColumna; c++) {

                boolean tieneFeromona = feromonas.contains(clave(f, c));
                boolean tieneHormiga = (f == fila && c == columna);

                filaVista.add(
                        new CeldaVista(
                                tieneFeromona,
                                tieneHormiga
                        )
                );
            }

            matriz.add(filaVista);
        }

        return new ResultadoSimulacion(
                matriz,
                fila,
                columna,
                direccion,
                movimientos
        );
    }

    /*
     * Método auxiliar para identificar una posición.
     */
    private String clave(int fila, int columna) {
        return fila + "," + columna;
    }
}
