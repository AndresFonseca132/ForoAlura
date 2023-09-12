package com.alura.latam.foroalura.domain.curso;

import jakarta.validation.constraints.NotNull;

// DTO para actualizar un curso, son los datos que recibimos del cliente y pueden ser actualizados
public record DatosActualizarCurso(
        @NotNull
        Long id,
        String nombre,
        String descripcion,
        String duracion,
        Lenguaje lenguaje
) {
}
