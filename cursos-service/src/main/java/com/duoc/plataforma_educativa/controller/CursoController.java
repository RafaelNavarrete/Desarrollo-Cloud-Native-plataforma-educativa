package com.duoc.plataforma_educativa.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.plataforma_educativa.model.Curso;
import com.duoc.plataforma_educativa.services.CursoServices;

/** 
 * Clase controlador que maneja las solicitudes HTTP relacionadas con los cursos.
 * Define endpoints para listar todos los cursos disponibles y agregar nuevos cursos
 * a la oferta educativa de la plataforma.
 *
 * @author Rafael Navarrete
 */

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoServices cursoServices;

    // Constructor para inyectar el servicio de cursos
    public CursoController(CursoServices cursoServices) {
        this.cursoServices = cursoServices;
    }

    // Endpoint para listar los cursos activos ordenados por nombre
    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        try {
            List<Curso> cursos = cursoServices.listarCursos();
            return ResponseEntity.ok(cursos);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para agregar un nuevo curso
    @PostMapping
    public ResponseEntity<Curso> agregarCurso(@RequestBody Curso curso) {
        try {
            Curso nuevoCurso = cursoServices.agregarCurso(curso);
            return ResponseEntity.ok(nuevoCurso);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
        
}