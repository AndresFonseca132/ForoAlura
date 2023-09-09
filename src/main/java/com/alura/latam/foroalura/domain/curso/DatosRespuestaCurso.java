package com.alura.latam.foroalura.domain.curso;

import com.alura.latam.foroalura.domain.respuesta.Respuesta;
import jakarta.validation.constraints.NotNull;

public record DatosRespuestaCurso(Long id, String nombre, String descripcion, String duracion, Lenguaje lenguaje) {

        public DatosRespuestaCurso(@NotNull Curso curso) {
            this(curso.getId(), curso.getNombre(), curso.getDescripcion(), curso.getDuracion(), curso.getLenguaje());
        }
}
