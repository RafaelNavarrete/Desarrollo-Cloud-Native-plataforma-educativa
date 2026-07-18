package com.duoc.bff_inscripciones.model;

/**
 * Clase modelo que representa el resumen de una inscripcion a un curso.
 * Contiene los datos basicos necesarios para generar el archivo PDF
 * del resumen y para publicarlo en la cola de mensajeria.
 *
 * @author Rafael Navarrete
 */
public class ResumenInscripcion {

    private String numeroResumen;
    private String estudiante;
    private String curso;
    private String fecha; // formato ddMMyyyy, ej: 06062026

    public ResumenInscripcion() {}

    public ResumenInscripcion(String numeroResumen, String estudiante, String curso, String fecha) {
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
