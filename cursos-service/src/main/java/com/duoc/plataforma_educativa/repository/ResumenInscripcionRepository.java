package com.duoc.plataforma_educativa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duoc.plataforma_educativa.model.ResumenInscripcionOracle;

/**
 * Repositorio JPA para la entidad ResumenInscripcionOracle.
 * Persiste los resumenes de inscripcion ya procesados desde la cola
 * en la tabla resumenes_procesados de Oracle Cloud.
 *
 * @author Rafael Navarrete
 */
@Repository
public interface ResumenInscripcionRepository extends JpaRepository<ResumenInscripcionOracle, String> {
}
