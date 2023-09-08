package com.alura.latam.foroalura.domain.course;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nombre;
    String descripcion;
    String duracion;
    @Enumerated(EnumType.STRING)
    Lenguaje lenguaje;

    public Curso(DatosRegistroCurso datosRegistroCurso) {
        this.nombre = datosRegistroCurso.nombre();
        this.descripcion = datosRegistroCurso.descripcion();
        this.duracion = datosRegistroCurso.duracion();
        this.lenguaje = datosRegistroCurso.lenguaje();
    }

    public void actualizarDatos(@Valid DatosActualizarCurso datosRespuestaCurso) {
        if (datosRespuestaCurso.nombre() != null) {
            this.nombre = datosRespuestaCurso.nombre();
        }
        if (datosRespuestaCurso.descripcion() != null){
            this.descripcion = datosRespuestaCurso.descripcion();
        }
        if (datosRespuestaCurso.duracion() != null) {
            this.duracion = datosRespuestaCurso.duracion();
        }
        if (datosRespuestaCurso.lenguaje() != null) {
            this.lenguaje = datosRespuestaCurso.lenguaje();
        }
    }

}

