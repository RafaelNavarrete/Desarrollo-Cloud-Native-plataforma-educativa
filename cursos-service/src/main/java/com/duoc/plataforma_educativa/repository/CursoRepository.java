package com.duoc.plataforma_educativa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duoc.plataforma_educativa.model.Curso;

/**
 * Repositorio JPA para la entidad Curso.
 * Proporciona automáticamente los métodos de acceso a datos
 * como guardar, buscar, listar y eliminar cursos.
 *
 * @author Rafael Navarrete
 */

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {    
}