package com.alura.latam.foroalura.domain.course;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarCurso(
        @NotNull
        Long id,
        String nombre,
        String descripcion,
        String duracion,
        Lenguaje lenguaje
) {
}
