package com.duoc.plataforma_educativa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.plataforma_educativa.model.Estudiante;

/**
 * Repositorio JPA para la entidad Estudiante.
 * Proporciona automáticamente los métodos de acceso a datos
 * como guardar, buscar, listar y eliminar estudiantes.
 *
 * @author Rafael Navarrete
 */

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
}