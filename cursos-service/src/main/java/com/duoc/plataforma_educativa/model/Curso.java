package com.duoc.plataforma_educativa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Clase modelo que representa un curso dentro de la plataforma educativa.
 * Define la estructura de datos de un curso, incluyendo su identificador,
 * nombre, instructor, duración en horas y costo de inscripción.
 * Es utilizada por las demás capas de la arquitectura para transportar
 * la información de los cursos.
 *
 * @author Rafael Navarrete
 */

@Entity
@Table
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    private String nombre;
    private String instructor;
    private int duracion;
    private double costo;

    // constructores, getters y setters
    public Curso() {}

    public Curso(String nombre, String instructor, int duracion, double costo) {
        this.nombre = nombre;
        this.instructor = instructor;
        this.duracion = duracion;
        this.costo = costo;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getInstructor() { return instructor; }
    public int getDuracion() { return duracion; }
    public double getCosto() { return costo; }

    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    public void setDuracion(int duracion) { this.duracion = duracion; }
    public void setCosto(double costo) { this.costo = costo; }   
}
