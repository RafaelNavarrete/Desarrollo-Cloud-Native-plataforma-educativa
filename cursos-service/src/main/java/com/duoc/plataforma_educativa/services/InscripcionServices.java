package com.duoc.plataforma_educativa.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duoc.plataforma_educativa.model.Curso;
import com.duoc.plataforma_educativa.model.Estudiante;
import com.duoc.plataforma_educativa.model.Inscripcion;
import com.duoc.plataforma_educativa.repository.CursoRepository;
import com.duoc.plataforma_educativa.repository.EstudianteRepository;
import com.duoc.plataforma_educativa.repository.InscripcionRepository;

/**
 * Clase de servicio que contiene la lógica de negocio relacionada con las inscripciones.
 * Actúa como intermediario entre el repositorio y el controlador,
 * gestionando las operaciones CRUD de la entidad Inscripcion.
 *
 * @author Rafael Navarrete
 */

@Service
public class InscripcionServices {

    // repositorios inyectados por constructor
    private final InscripcionRepository inscripcionRepository;
    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;

    // constructor para inyectar el repositorio de inscripciones
    public InscripcionServices(InscripcionRepository inscripcionRepository, EstudianteRepository estudianteRepository, CursoRepository cursoRepository) {
        this.inscripcionRepository = inscripcionRepository;
        this.estudianteRepository = estudianteRepository;
        this.cursoRepository = cursoRepository;
    }

    public Inscripcion inscribirEstudiante(Long estudianteId, List<Long> cursosIds) {
        // Buscar el estudiante
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        // Buscar los cursos seleccionados
        List<Curso> cursos = cursoRepository.findAllById(cursosIds);
        if (cursos.isEmpty()) {
            throw new RuntimeException("No se encontraron cursos válidos");
        }

        // Calcular el total sumando el costo de cada curso
        double total = cursos.stream()
                .mapToDouble(Curso::getCosto)
                .sum();

        // Crear y guardar la inscripción
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setEstudiante(estudiante);
        inscripcion.setCursos(cursos);
        inscripcion.setTotal(total);

        return inscripcionRepository.save(inscripcion);
    }
}