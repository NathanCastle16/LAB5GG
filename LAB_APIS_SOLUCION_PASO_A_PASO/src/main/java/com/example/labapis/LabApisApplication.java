package com.example.labapis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * CLASE PRINCIPAL
 *
 * @SpringBootApplication inicia Spring Boot y permite que Spring
 * encuentre los controladores, entidades y repositorios que están
 * dentro de este paquete y sus subpaquetes.
 */
@SpringBootApplication
public class LabApisApplication {

    public static void main(String[] args) {
        // Inicia toda la aplicación.
        SpringApplication.run(LabApisApplication.class, args);
    }
}
