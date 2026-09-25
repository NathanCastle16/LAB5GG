# Caso: La Hormiga Charlie

Solución en Spring Boot + Spring MVC + Thymeleaf, usando solamente conceptos trabajados en los PPT del curso y lógica Java básica.

## Qué hace

La aplicación implementa las reglas del enunciado:

- Si Charlie encuentra una **miga**:
  - deja una feromona,
  - gira 90° a la derecha,
  - avanza una celda.

- Si Charlie encuentra una **feromona**:
  - deja una miga,
  - gira 90° a la izquierda,
  - avanza una celda.

- Si Charlie sale de la zona visible, la zona crece usando el valor indicado en **Aumento de visión**.

## Configuración

Ejemplo del enunciado:

- Dimensión inicial: `5,5`
- Aumento de visión: `4`
- Posición inicial: `2,2`
- Dirección inicial: `R`

Direcciones:

- `R`: derecha
- `L`: izquierda
- `U`: arriba
- `D`: abajo

## Flujo MVC

```text
GET /
  -> configuracion.html

POST /configurar
  -> Data Binding a ConfiguracionHormiga
  -> cantidad-movimientos.html

POST /simular
  -> crea SimuladorCharlie
  -> ejecuta N movimientos
  -> manda ResultadoSimulacion al Model
  -> resultado.html
```

## Conceptos del curso usados

- `@Controller`
- `@GetMapping`
- `@PostMapping`
- `@ModelAttribute`
- `Model`
- Data Binding
- Thymeleaf
- `th:object`
- `th:field`
- `th:each`
- `th:if`

No se añadió JPA, base de datos, DTO, Service, JavaScript ni otras tecnologías porque el caso mostrado no las solicita.

## Cómo ejecutar

1. Abrir el proyecto en IntelliJ.
2. Esperar a que Maven descargue las dependencias.
3. Ejecutar `HormigaCharlieApplication`.
4. Abrir `http://localhost:8080/`.

## Nota sobre "Cantidad de foto por actualización"

En esta solución ese campo se interpreta como la cantidad de movimientos que Charlie ejecuta antes de mostrar la fotografía resultante. Así, si se ingresa `20000`, se genera el estado correspondiente al movimiento 20000, como en la figura del enunciado.
