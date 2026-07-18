package com.duoc.plataforma_educativa.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.plataforma_educativa.model.Estudiante;
import com.duoc.plataforma_educativa.services.EstudianteServices;

/**
 * Clase controlador que maneja las solicitudes HTTP relacionadas con los estudiantes.
 * Define endpoints para listar todos los estudiantes registrados y agregar
 * nuevos estudiantes a la plataforma educativa.
 *
 * @author Rafael Navarrete
 */
@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final EstudianteServices estudianteServices;

    // Constructor para inyectar el servicio de estudiantes
    public EstudianteController(EstudianteServices estudianteServices) {
        this.estudianteServices = estudianteServices;
    }

    // Endpoint para listar todos los estudiantes
    @GetMapping
    public ResponseEntity<List<Estudiante>> listarEstudiantes() {
        try {
            List<Estudiante> estudiantes = estudianteServices.listarEstudiantes();
            return ResponseEntity.ok(estudiantes);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para agregar un nuevo estudiante
    @PostMapping
    public ResponseEntity<Estudiante> agregarEstudiante(@RequestBody Estudiante estudiante) {
        try {
            Estudiante nuevoEstudiante = estudianteServices.agregarEstudiante(estudiante);
            return ResponseEntity.ok(nuevoEstudiante);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}