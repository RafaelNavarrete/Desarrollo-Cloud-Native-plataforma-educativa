package com.duoc.plataforma_educativa.model;

import jakarta.persistence.*;
import java.util.List;

/**
 * Clase modelo que representa una inscripción dentro de la plataforma educativa.
 * Define la estructura de datos de una inscripción, relacionando un estudiante
 * con uno o más cursos seleccionados y calculando el costo total a pagar.
 * Es utilizada por las demás capas de la arquitectura para transportar
 * la información de las inscripciones.
 *
 * @author Rafael Navarrete
 */

@Entity
@Table(name = "inscripciones")
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @ManyToMany
    @JoinTable(
        name = "inscripcion_cursos",
        joinColumns = @JoinColumn(name = "inscripcion_id"),
        inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private List<Curso> cursos;

    private double total;

    public Inscripcion() {}

    public Long getId() { return id; }
    public Estudiante getEstudiante() { return estudiante; }
    public List<Curso> getCursos() { return cursos; }
    public double getTotal() { return total; }

    public void setId(Long id) { this.id = id; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }
    public void setCursos(List<Curso> cursos) { this.cursos = cursos; }
    public void setTotal(double total) { this.total = total; }
}