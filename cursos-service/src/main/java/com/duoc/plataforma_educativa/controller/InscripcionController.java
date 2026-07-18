package com.duoc.plataforma_educativa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.plataforma_educativa.model.Inscripcion;
import com.duoc.plataforma_educativa.model.InscripcionRequest;
import com.duoc.plataforma_educativa.services.InscripcionServices;

/**
 * Clase controlador que maneja las solicitudes HTTP relacionadas con las inscripciones.
 * Define un endpoint para que los estudiantes puedan inscribirse en uno o más cursos,
 * retornando un resumen con los cursos seleccionados y el total a pagar.
 *
 * @author Rafael Navarrete
 */
@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {

    private final InscripcionServices inscripcionServices;

    // Constructor para inyectar el servicio de inscripciones
    public InscripcionController(InscripcionServices inscripcionServices) {
        this.inscripcionServices = inscripcionServices;
    }

    // Endpoint para inscribir un estudiante en uno o más cursos
    @PostMapping
    public ResponseEntity<Inscripcion> inscribir(@RequestBody InscripcionRequest request) {
        try {
            Inscripcion inscripcion = inscripcionServices.inscribirEstudiante(
                request.getEstudianteId(),
                request.getCursosIds()
            );
            return ResponseEntity.ok(inscripcion);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
