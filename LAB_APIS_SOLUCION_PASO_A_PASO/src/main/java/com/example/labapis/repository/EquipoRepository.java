package com.example.labapis.repository;

import com.example.labapis.entity.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * REPOSITORIO DE EQUIPO
 *
 * JpaRepository<Equipo, Integer>
 * Equipo -> entidad.
 * Integer -> tipo del ID.
 *
 * Usaremos findAll() para obtener los equipos del desplegable.
 */
@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {
}
