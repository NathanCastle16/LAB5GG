package com.example.hormigacharlie.model;

/*
 * OBJETO PARA LA VISTA
 *
 * Cada objeto representa una celda de la cuadrícula.
 * feromona = true  -> celda negra.
 * hormiga   = true -> posición actual de Charlie.
 */
public class CeldaVista {

    private boolean feromona;
    private boolean hormiga;

    public CeldaVista(boolean feromona, boolean hormiga) {
        this.feromona = feromona;
        this.hormiga = hormiga;
    }

    public boolean isFeromona() {
        return feromona;
    }

    public boolean isHormiga() {
        return hormiga;
    }
}
