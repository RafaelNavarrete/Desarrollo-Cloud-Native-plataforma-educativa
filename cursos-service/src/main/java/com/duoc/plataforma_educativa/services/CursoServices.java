package com.duoc.plataforma_educativa.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duoc.plataforma_educativa.model.Curso;
import com.duoc.plataforma_educativa.repository.CursoRepository;

/**
 * Clase de servicio que contiene la lógica de negocio relacionada con los cursos.
 * Actúa como intermediario entre el repositorio y el controlador,
 * gestionando las operaciones CRUD de la entidad Curso.
 *
 * @author Rafael Navarrete
 */

@Service
public class CursoServices {

    // repositorio inyectado por constructor
    private final CursoRepository cursoRepository;

    // constructor para inyectar el repositorio de cursos
    public CursoServices(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    // Método Get para obtener todos los cursos
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    // Método Post para guardar un nuevo curso
    public Curso agregarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }
    
}