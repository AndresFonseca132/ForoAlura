package com.alura.latam.foroalura.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroRespuesta(@NotBlank
                                     String contenido,
                                     @NotNull
                                     Long usuario_id,
                                     @NotNull
                                     Long topico_id) {
}
