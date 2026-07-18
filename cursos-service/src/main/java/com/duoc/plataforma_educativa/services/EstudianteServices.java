package com.duoc.plataforma_educativa.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duoc.plataforma_educativa.model.Estudiante;
import com.duoc.plataforma_educativa.repository.EstudianteRepository;

/**
 * Clase de servicio que contiene la lógica de negocio relacionada con los estudiantes.
 * Actúa como intermediario entre el repositorio y el controlador,
 * gestionando las operaciones CRUD de la entidad Estudiante.
 *
 * @author Rafael Navarrete
 */

@Service
public class EstudianteServices {
    
    // repositorio inyectado por constructor
    private final EstudianteRepository estudianteRepository;

    // constructor para inyectar el repositorio de estudiantes
    public EstudianteServices(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    // Método Get para obtener todos los estudiantes
    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.findAll();
    }

    // Método Post para guardar un nuevo estudiante
    public Estudiante agregarEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

}