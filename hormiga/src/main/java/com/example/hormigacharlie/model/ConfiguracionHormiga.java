package com.example.hormigacharlie.model;

/*
 * MODELO DE CONFIGURACIÓN
 *
 * Este objeto recibe los datos del formulario mediante Data Binding.
 * Por eso los nombres de los campos del HTML deben coincidir con
 * los atributos de esta clase.
 */
public class ConfiguracionHormiga {

    // Ejemplo: "5,5"
    private String dimensionInicial;

    // Cantidad de celdas que aumentará la zona visible al llegar a un borde.
    private Integer aumentoVision;

    // Ejemplo: "2,2"
    private String posicionInicial;

    // Direcciones permitidas: R, L, U, D.
    private String direccionInicial;

    // Cantidad de movimientos que se desea simular.
    private Integer cantidadMovimientos;

    public ConfiguracionHormiga() {
    }

    public String getDimensionInicial() {
        return dimensionInicial;
    }

    public void setDimensionInicial(String dimensionInicial) {
        this.dimensionInicial = dimensionInicial;
    }

    public Integer getAumentoVision() {
        return aumentoVision;
    }

    public void setAumentoVision(Integer aumentoVision) {
        this.aumentoVision = aumentoVision;
    }

    public String getPosicionInicial() {
        return posicionInicial;
    }

    public void setPosicionInicial(String posicionInicial) {
        this.posicionInicial = posicionInicial;
    }

    public String getDireccionInicial() {
        return direccionInicial;
    }

    public void setDireccionInicial(String direccionInicial) {
        this.direccionInicial = direccionInicial;
    }

    public Integer getCantidadMovimientos() {
        return cantidadMovimientos;
    }

    public void setCantidadMovimientos(Integer cantidadMovimientos) {
        this.cantidadMovimientos = cantidadMovimientos;
    }
}
