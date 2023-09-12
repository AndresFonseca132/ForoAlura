package com.alura.latam.foroalura.domain.topic;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

// Se especifican los datos que seran recibidos para actualizar un topico
public record DatosActualizarTopico(
        String titulo,
        String mensaje,
        Estado estado,
        LocalDateTime fechaCreacion) {
}
