# LAB - Catálogo de APIs

Solución comentada paso a paso, basada en los temas vistos en clase.

## Temas usados
- Spring MVC
- Spring Data JPA
- Relaciones entre tablas
- @ManyToOne
- @JoinColumn
- JpaRepository
- @Query y @Param
- Data Binding
- @ModelAttribute
- Thymeleaf
- RedirectAttributes / Flash Attributes

No se agregaron capas Service, DTO, validaciones ni patrones que el enunciado no solicita.

## Base de datos
El laboratorio usa `api_registry_db`.

Si el profesor entregó un script SQL oficial, use ese script. Se incluye un SQL de apoyo en `database/api_registry_db.sql` basado en el diagrama mostrado.

## Antes de ejecutar
1. Cree/cargue `api_registry_db` en MySQL.
2. Revise `src/main/resources/application.properties`.
3. Coloque su contraseña de MySQL si corresponde.
4. Ejecute `LabApisApplication`.
5. Abra `http://localhost:8080/`.

## Rutas
- `GET /` -> listado.
- `GET /api/endpoints?id=1` -> detalle y endpoints.
- `GET /api/endpoints?id=1&metodo=GET` -> filtro.
- `GET /api/nuevo` -> nueva API.
- `GET /api/editar?id=1` -> edición.
- `POST /api/guardar` -> crear/actualizar.

## Relación
```text
EQUIPO
  1
  |
  N
 API
  1
  |
  N
ENDPOINT
```

Desde `Api`:
```java
@ManyToOne
@JoinColumn(name = "equipo_id")
private Equipo equipo;
```

Desde `Endpoint`:
```java
@ManyToOne
@JoinColumn(name = "api_id")
private Api api;
```

## RedirectAttributes
Después de guardar:
```java
attr.addFlashAttribute("msg", "API registrada correctamente");
return "redirect:/";
```

El mensaje está disponible en la siguiente solicitud y después desaparece.

## Comentarios
Todos los archivos Java principales contienen comentarios explicando qué hace cada sección.
