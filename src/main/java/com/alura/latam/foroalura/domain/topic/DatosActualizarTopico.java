package com.alura.latam.foroalura.domain.topic;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosActualizarTopico(
        String titulo,
        String mensaje,
        Estado estado,
        LocalDateTime fechaCreacion) {
}
