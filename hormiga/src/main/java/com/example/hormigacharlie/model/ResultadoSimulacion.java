package com.example.hormigacharlie.model;

import java.util.List;

/*
 * RESULTADO DE LA SIMULACIÓN
 *
 * Se usa para enviar al HTML:
 * - la matriz visible,
 * - la posición final,
 * - la dirección final,
 * - y la cantidad de movimientos realizados.
 */
public class ResultadoSimulacion {

    private List<List<CeldaVista>> matriz;
    private int filaHormiga;
    private int columnaHormiga;
    private String direccion;
    private int movimientos;

    public ResultadoSimulacion(List<List<CeldaVista>> matriz,
                               int filaHormiga,
                               int columnaHormiga,
                               String direccion,
                               int movimientos) {
        this.matriz = matriz;
        this.filaHormiga = filaHormiga;
        this.columnaHormiga = columnaHormiga;
        this.direccion = direccion;
        this.movimientos = movimientos;
    }

    public List<List<CeldaVista>> getMatriz() {
        return matriz;
    }

    public int getFilaHormiga() {
        return filaHormiga;
    }

    public int getColumnaHormiga() {
        return columnaHormiga;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getMovimientos() {
        return movimientos;
    }
}
