package com.example.labapis.controller;

import com.example.labapis.entity.Api;
import com.example.labapis.entity.Endpoint;
import com.example.labapis.entity.Equipo;
import com.example.labapis.repository.ApiRepository;
import com.example.labapis.repository.EndpointRepository;
import com.example.labapis.repository.EquipoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/*
 * CONTROLADOR PRINCIPAL
 *
 * EJERCICIO 1: listado de APIs.
 * EJERCICIO 2: detalle, endpoints y filtro.
 * EJERCICIO 3: registro, edición y mensajes flash.
 */
@Controller
public class ApiController {

    private final ApiRepository apiRepository;
    private final EquipoRepository equipoRepository;
    private final EndpointRepository endpointRepository;

    /* Spring inyecta los repositorios mediante el constructor. */
    public ApiController(ApiRepository apiRepository,
                         EquipoRepository equipoRepository,
                         EndpointRepository endpointRepository) {
        this.apiRepository = apiRepository;
        this.equipoRepository = equipoRepository;
        this.endpointRepository = endpointRepository;
    }

    // ============================================================
    // EJERCICIO 1 - LISTADO DE APIs
    // ============================================================

    /*
     * GET /
     * 1. Obtiene todas las APIs con findAll().
     * 2. Las envía a la vista con Model.
     * 3. Retorna templates/api/list.html.
     */
    @GetMapping("/")
    public String listarApis(Model model) {
        model.addAttribute("apis", apiRepository.findAll());
        return "api/list";
    }

    // ============================================================
    // EJERCICIO 2 - DETALLE Y FILTRO DE ENDPOINTS
    // ============================================================

    /*
     * Ejemplos:
     * /api/endpoints?id=1
     * /api/endpoints?id=1&metodo=GET
     */
    @GetMapping("/api/endpoints")
    public String detalleApi(@RequestParam("id") Integer id,
                             @RequestParam(value = "metodo", required = false) String metodo,
                             Model model) {

        // Buscamos la API por ID.
        Optional<Api> apiOptional = apiRepository.findById(id);

        // Si no existe, regresamos al listado principal.
        if (apiOptional.isEmpty()) {
            return "redirect:/";
        }

        Api api = apiOptional.get();
        List<Endpoint> endpoints;

        /*
         * Si no llega método, se muestran todos los endpoints.
         * Si llega GET/POST/PUT/DELETE, se aplica el filtro.
         */
        if (metodo == null || metodo.trim().isEmpty()) {
            endpoints = endpointRepository.listarPorApi(id);
        } else {
            endpoints = endpointRepository.listarPorApiYMetodo(id, metodo);
        }

        // Enviamos la API, los endpoints y el filtro a la vista.
        model.addAttribute("api", api);
        model.addAttribute("endpoints", endpoints);
        model.addAttribute("metodo", metodo);

        return "api/detail";
    }

    // ============================================================
    // EJERCICIO 3 - NUEVA API
    // ============================================================

    /* GET /api/nuevo -> muestra el formulario vacío. */
    @GetMapping("/api/nuevo")
    public String nuevaApi(Model model) {

        // Creamos una API vacía para el formulario.
        Api api = new Api();

        // Preparamos Equipo para poder enlazar equipo.id en el formulario.
        api.setEquipo(new Equipo());

        model.addAttribute("api", api);

        // Enviamos todos los equipos para el desplegable.
        model.addAttribute("equipos", equipoRepository.findAll());

        return "api/form";
    }

    // ============================================================
    // EJERCICIO 3 - EDITAR API
    // ============================================================

    /* GET /api/editar?id=2 -> carga los datos actuales. */
    @GetMapping("/api/editar")
    public String editarApi(@RequestParam("id") Integer id,
                            Model model) {

        Optional<Api> apiOptional = apiRepository.findById(id);

        // El enunciado indica redirigir si el ID no existe.
        if (apiOptional.isEmpty()) {
            return "redirect:/";
        }

        // La API existente llena automáticamente el formulario.
        model.addAttribute("api", apiOptional.get());

        // Volvemos a enviar los equipos para el desplegable.
        model.addAttribute("equipos", equipoRepository.findAll());

        return "api/form";
    }

    // ============================================================
    // EJERCICIO 3 - GUARDAR / ACTUALIZAR
    // ============================================================

    /*
     * POST /api/guardar
     *
     * @ModelAttribute recibe el objeto Api mediante Data Binding.
     * RedirectAttributes se usa porque después hacemos redirect y
     * necesitamos mostrar un mensaje temporal una sola vez.
     */
    @PostMapping("/api/guardar")
    public String guardarApi(@ModelAttribute("api") Api api,
                             RedirectAttributes attr) {

        /*
         * Revisamos el ID ANTES de save().
         * null -> nueva API.
         * con valor -> edición.
         */
        boolean esNueva = api.getId() == null;

        /*
         * El formulario envía el ID del equipo seleccionado.
         * Buscamos ese Equipo y lo colocamos dentro de Api.
         */
        if (api.getEquipo() != null && api.getEquipo().getId() != null) {
            Optional<Equipo> equipoOptional =
                    equipoRepository.findById(api.getEquipo().getId());

            if (equipoOptional.isPresent()) {
                api.setEquipo(equipoOptional.get());
            }
        }

        if (esNueva) {
            /* La fecha no está en el formulario: se asigna automáticamente. */
            api.setFechaRegistro(LocalDate.now());
        } else {
            /* En edición la fecha original NO debe cambiar. */
            Optional<Api> apiActual = apiRepository.findById(api.getId());

            if (apiActual.isEmpty()) {
                return "redirect:/";
            }

            api.setFechaRegistro(apiActual.get().getFechaRegistro());
        }

        /* save() crea si es nueva y actualiza si ya tiene ID. */
        apiRepository.save(api);

        /* Mensajes distintos y temporales para creación/edición. */
        if (esNueva) {
            attr.addFlashAttribute("msg", "API registrada correctamente");
        } else {
            attr.addFlashAttribute("msg", "API actualizada correctamente");
        }

        return "redirect:/";
    }
}
