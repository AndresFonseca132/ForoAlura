package com.alura.latam.foroalura.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// Clase record que contiene los datos necesarios para registrar una respuesta, ademas de las anotaciones de validacion
public record DatosRegistroRespuesta(@NotBlank
                                     String contenido,
                                     @NotNull
                                     Long usuario_id,
                                     @NotNull
                                     Long topico_id) {
}
