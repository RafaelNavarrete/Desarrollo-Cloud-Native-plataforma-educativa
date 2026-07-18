package com.duoc.plataforma_educativa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duoc.plataforma_educativa.model.Inscripcion;

/**
 * Repositorio JPA para la entidad Inscripcion.
 * Proporciona automáticamente los métodos de acceso a datos
 * como guardar, buscar, listar y eliminar inscripciones.
 *
 * @author Rafael Navarrete
 */

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {   
}