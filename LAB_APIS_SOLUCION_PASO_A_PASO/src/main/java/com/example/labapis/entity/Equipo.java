package com.example.labapis.entity;

import jakarta.persistence.*;

/*
 * ENTIDAD EQUIPO
 *
 * Representa la tabla "equipos".
 *
 * Relación del modelo:
 *
 *      EQUIPO 1 -------- N API
 *
 * Un equipo puede tener varias APIs.
 * Para resolver este laboratorio no necesitamos colocar @OneToMany
 * aquí, porque desde Api ya podemos llegar al Equipo con @ManyToOne.
 */
@Entity
@Table(name = "equipos")
public class Equipo {

    /*
     * @Id indica que "id" es la clave primaria.
     * @GeneratedValue indica que MySQL genera el ID automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "area")
    private String area;

    // JPA necesita un constructor vacío.
    public Equipo() {
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
