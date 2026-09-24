-- SQL DE APOYO BASADO EN EL MODELO MOSTRADO EN EL LABORATORIO.
-- Si el profesor entregó un script oficial, use el script oficial.

DROP DATABASE IF EXISTS api_registry_db;
CREATE DATABASE api_registry_db;
USE api_registry_db;

CREATE TABLE equipos (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    area VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE apis (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(150) NOT NULL,
    version VARCHAR(20) NOT NULL,
    fecha_registro DATE NOT NULL,
    estado VARCHAR(20) NOT NULL,
    equipo_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (equipo_id) REFERENCES equipos(id)
);

CREATE TABLE endpoints (
    id INT NOT NULL AUTO_INCREMENT,
    ruta VARCHAR(255) NOT NULL,
    metodo_http VARCHAR(10) NOT NULL,
    descripcion VARCHAR(255),
    api_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (api_id) REFERENCES apis(id)
);

INSERT INTO equipos(nombre, area) VALUES
('Backend', 'Tecnología'),
('Mobile', 'Aplicaciones'),
('Data', 'Analítica');

INSERT INTO apis(nombre, version, fecha_registro, estado, equipo_id) VALUES
('API Usuarios', 'v1', '2026-09-01', 'Activa', 1),
('API Reportes', 'v2', '2026-09-05', 'Activa', 3),
('API Antigua', 'v1', '2026-08-20', 'Deprecada', 1);

INSERT INTO endpoints(ruta, metodo_http, descripcion, api_id) VALUES
('/users', 'GET', 'Lista usuarios', 1),
('/users', 'POST', 'Registra un usuario', 1),
('/users/{id}', 'PUT', 'Actualiza un usuario', 1),
('/reports', 'GET', 'Lista reportes', 2),
('/reports/{id}', 'DELETE', 'Elimina un reporte', 2);
