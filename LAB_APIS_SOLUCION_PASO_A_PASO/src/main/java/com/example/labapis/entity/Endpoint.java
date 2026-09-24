package com.example.labapis.entity;

import jakarta.persistence.*;

/*
 * ENTIDAD ENDPOINT
 *
 * Representa la tabla "endpoints".
 *
 * Relación:
 *
 *      API 1 -------- N ENDPOINT
 *
 * Una API puede tener varios endpoints.
 * Cada endpoint pertenece a una sola API.
 */
@Entity
@Table(name = "endpoints")
public class Endpoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ruta")
    private String ruta;

    @Column(name = "metodo_http")
    private String metodoHttp;

    @Column(name = "descripcion")
    private String descripcion;

    /*
     * Muchos endpoints pertenecen a una API.
     * La columna que une endpoints con apis es api_id.
     */
    @ManyToOne
    @JoinColumn(name = "api_id")
    private Api api;

    public Endpoint() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getMetodoHttp() {
        return metodoHttp;
    }

    public void setMetodoHttp(String metodoHttp) {
        this.metodoHttp = metodoHttp;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }
}
