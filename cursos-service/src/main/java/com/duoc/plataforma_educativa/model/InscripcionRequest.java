package com.duoc.plataforma_educativa.model;

import java.util.List;

/**
 * Clase auxiliar que representa el cuerpo de la solicitud de inscripción.
 * Contiene el identificador del estudiante y la lista de identificadores
 * de los cursos en los que desea inscribirse.
 *
 * @author Rafael Navarrete
 */
public class InscripcionRequest {

    private Long estudianteId;
    private List<Long> cursosIds;

    public Long getEstudianteId() { return estudianteId; }
    public List<Long> getCursosIds() { return cursosIds; }

    public void setEstudianteId(Long estudianteId) { this.estudianteId = estudianteId; }
    public void setCursosIds(List<Long> cursosIds) { this.cursosIds = cursosIds; }
}
