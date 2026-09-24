package com.example.labapis.repository;

import com.example.labapis.entity.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * REPOSITORIO DE API
 *
 * Métodos de JpaRepository que usamos:
 * findAll()  -> listar todas las APIs.
 * findById() -> buscar una API.
 * save()     -> crear o actualizar.
 */
@Repository
public interface ApiRepository extends JpaRepository<Api, Integer> {
}
