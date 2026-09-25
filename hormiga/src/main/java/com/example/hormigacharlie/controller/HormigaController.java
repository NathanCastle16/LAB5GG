package com.example.hormigacharlie.controller;

import com.example.hormigacharlie.model.ConfiguracionHormiga;
import com.example.hormigacharlie.model.ResultadoSimulacion;
import com.example.hormigacharlie.model.SimuladorCharlie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/*
 * CONTROLLER DE LA APLICACIÓN
 *
 * Aquí se reciben las solicitudes del navegador.
 *
 * Se usan solamente conceptos ya trabajados:
 * - @Controller
 * - @GetMapping
 * - @PostMapping
 * - @ModelAttribute
 * - Model
 */
@Controller
public class HormigaController {

    /*
     * GET /
     *
     * Muestra el formulario inicial.
     * Enviamos un objeto vacío llamado "configuracion" para que
     * Thymeleaf pueda usar th:object y th:field.
     */
    @GetMapping("/")
    public String inicio(Model model) {

        model.addAttribute(
                "configuracion",
                new ConfiguracionHormiga()
        );

        return "configuracion";
    }

    /*
     * POST /configurar
     *
     * Spring realiza Data Binding y llena ConfiguracionHormiga
     * con lo que el usuario escribió en el formulario.
     *
     * @ModelAttribute("configuracion") debe coincidir con
     * th:object="${configuracion}" en el HTML.
     */
    @PostMapping("/configurar")
    public String configurar(
            @ModelAttribute("configuracion") ConfiguracionHormiga configuracion,
            Model model) {

        /*
         * Validaciones sencillas para evitar que el programa falle
         * cuando el usuario escribe un formato incorrecto.
         * No se usan anotaciones nuevas porque el enunciado mostrado
         * no pide validación con Hibernate Validator.
         */
        String error = validarConfiguracion(configuracion);

        if (error != null) {
            model.addAttribute("error", error);
            return "configuracion";
        }

        /*
         * En esta segunda pantalla conservamos la configuración
         * y pedimos la cantidad de movimientos/foto a visualizar.
         */
        model.addAttribute("configuracion", configuracion);

        return "cantidad-movimientos";
    }

    /*
     * POST /simular
     *
     * Recibe nuevamente la configuración mediante Data Binding.
     * Luego crea el simulador y ejecuta la cantidad de movimientos.
     */
    @PostMapping("/simular")
    public String simular(
            @ModelAttribute("configuracion") ConfiguracionHormiga configuracion,
            Model model) {

        String error = validarConfiguracion(configuracion);

        if (error != null) {
            model.addAttribute("error", error);
            return "configuracion";
        }

        if (configuracion.getCantidadMovimientos() == null
                || configuracion.getCantidadMovimientos() < 0) {

            model.addAttribute(
                    "error",
                    "La cantidad de movimientos debe ser 0 o mayor."
            );

            return "cantidad-movimientos";
        }

        /*
         * "5,5" se transforma en filas=5 y columnas=5.
         */
        int[] dimension = separarPar(configuracion.getDimensionInicial());

        /*
         * "2,2" se transforma en fila=2 y columna=2.
         */
        int[] posicion = separarPar(configuracion.getPosicionInicial());

        /*
         * Creamos el simulador con la configuración del formulario.
         */
        SimuladorCharlie simulador = new SimuladorCharlie(
                dimension[0],
                dimension[1],
                posicion[0],
                posicion[1],
                configuracion.getDireccionInicial(),
                configuracion.getAumentoVision()
        );

        /*
         * Ejecutamos la simulación.
         */
        ResultadoSimulacion resultado = simulador.simular(
                configuracion.getCantidadMovimientos()
        );

        /*
         * Enviamos el resultado al HTML.
         */
        model.addAttribute("resultado", resultado);
        model.addAttribute("configuracion", configuracion);

        return "resultado";
    }

    /*
     * Convierte un texto como "5,5" en dos enteros.
     */
    private int[] separarPar(String texto) {

        String[] partes = texto.trim().split(",");

        return new int[]{
                Integer.parseInt(partes[0].trim()),
                Integer.parseInt(partes[1].trim())
        };
    }

    /*
     * Validación básica de los datos principales.
     */
    private String validarConfiguracion(ConfiguracionHormiga configuracion) {

        try {
            int[] dimension = separarPar(configuracion.getDimensionInicial());
            int[] posicion = separarPar(configuracion.getPosicionInicial());

            if (dimension[0] <= 0 || dimension[1] <= 0) {
                return "Las dimensiones deben ser mayores que 0.";
            }

            if (configuracion.getAumentoVision() == null
                    || configuracion.getAumentoVision() <= 0) {
                return "El aumento de visión debe ser mayor que 0.";
            }

            if (posicion[0] < 0 || posicion[0] >= dimension[0]
                    || posicion[1] < 0 || posicion[1] >= dimension[1]) {
                return "La posición inicial debe estar dentro de la zona inicial.";
            }

            String direccion = configuracion.getDireccionInicial();

            if (direccion == null) {
                return "Debe ingresar una dirección.";
            }

            direccion = direccion.trim().toUpperCase();

            if (!direccion.equals("R")
                    && !direccion.equals("L")
                    && !direccion.equals("U")
                    && !direccion.equals("D")) {
                return "La dirección debe ser R, L, U o D.";
            }

            configuracion.setDireccionInicial(direccion);

        } catch (Exception e) {
            return "Use el formato fila,columna. Ejemplo: 5,5 o 2,2.";
        }

        return null;
    }
}
