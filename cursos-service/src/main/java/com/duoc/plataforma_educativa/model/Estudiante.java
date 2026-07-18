package com.duoc.plataforma_educativa.model;

import jakarta.persistence.*;

/**
 * Clase modelo que representa un estudiante dentro de la plataforma educativa.
 * Define la estructura de datos de un estudiante, incluyendo su identificador,
 * nombre y correo electrónico de contacto.
 * Es utilizada por las demás capas de la arquitectura para transportar
 * la información de los estudiantes.
 *
 * @author Rafael Navarrete
 */

@Entity
@Table(name = "estudiantes")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;

    public Estudiante() {}

    public Estudiante(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }

    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEmail(String email) { this.email = email; }
}