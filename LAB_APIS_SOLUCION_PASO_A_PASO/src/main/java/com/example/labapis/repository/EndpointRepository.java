package com.example.labapis.repository;

import com.example.labapis.entity.Endpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * REPOSITORIO DE ENDPOINT
 *
 * Se usa @Query porque es un tema ya trabajado.
 * Esto es JPQL, por eso usamos los atributos JAVA:
 * e.api.id y e.metodoHttp.
 */
@Repository
public interface EndpointRepository extends JpaRepository<Endpoint, Integer> {

    /* Todos los endpoints de una API. */
    @Query("""
            SELECT e
            FROM Endpoint e
            WHERE e.api.id = :apiId
            ORDER BY e.id
            """)
    List<Endpoint> listarPorApi(
            @Param("apiId") Integer apiId
    );

    /* Endpoints de una API filtrados por método HTTP. */
    @Query("""
            SELECT e
            FROM Endpoint e
            WHERE e.api.id = :apiId
            AND e.metodoHttp = :metodo
            ORDER BY e.id
            """)
    List<Endpoint> listarPorApiYMetodo(
            @Param("apiId") Integer apiId,
            @Param("metodo") String metodo
    );
}
