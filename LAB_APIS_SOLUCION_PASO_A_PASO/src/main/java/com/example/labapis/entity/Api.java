package com.example.labapis.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

/*
 * ENTIDAD API
 *
 * Representa la tabla "apis".
 *
 * Muchas APIs pueden pertenecer al mismo Equipo.
 * Por eso, mirando la relación desde Api, usamos @ManyToOne.
 */
@Entity
@Table(name = "apis")
public class Api {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "version")
    private String version;

    /* La columna MySQL se llama fecha_registro y es DATE. */
    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @Column(name = "estado")
    private String estado;

    /*
     * RELACIÓN ENTRE TABLAS
     *
     * @ManyToOne: muchas APIs pueden pertenecer a un Equipo.
     * @JoinColumn(name = "equipo_id"): equipo_id es la columna REAL
     * de la tabla apis que contiene la llave foránea hacia equipos.
     *
     * Luego podemos usar:
     * api.getEquipo().getNombre()
     * o en Thymeleaf: ${api.equipo.nombre}
     */
    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;

    public Api() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
