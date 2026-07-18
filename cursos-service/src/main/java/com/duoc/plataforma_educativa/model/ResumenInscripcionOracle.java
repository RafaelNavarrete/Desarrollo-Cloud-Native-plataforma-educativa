package com.duoc.plataforma_educativa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad JPA que representa un resumen de inscripcion ya procesado,
 * persistido en una tabla nueva de Oracle Cloud una vez que el mensaje
 * es consumido desde la cola RabbitMQ (resumenes_queue).
 *
 * @author Rafael Navarrete
 */
@Entity
@Table(name = "resumenes_procesados")
public class ResumenInscripcionOracle {

    @Id
    private String numeroResumen;
    private String estudiante;
    private String curso;
    private String fecha;

    public ResumenInscripcionOracle() {}

    public ResumenInscripcionOracle(String numeroResumen, String estudiante, String curso, String fecha) {
        this.numeroResumen = numeroResumen;
        this.estudiante = estudiante;
        this.curso = curso;
        this.fecha = fecha;
    }

    public String getNumeroResumen() { return numeroResumen; }
    public String getEstudiante() { return estudiante; }
    public String getCurso() { return curso; }
    public String getFecha() { return fecha; }

    public void setNumeroResumen(String numeroResumen) { this.numeroResumen = numeroResumen; }
    public void setEstudiante(String estudiante) { this.estudiante = estudiante; }
    public void setCurso(String curso) { this.curso = curso; }
    public void setFecha(String fecha) { this.fecha = fecha; }
}
