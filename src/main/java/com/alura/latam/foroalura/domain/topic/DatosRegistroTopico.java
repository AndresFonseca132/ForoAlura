package com.alura.latam.foroalura.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(@NotBlank
                                  String titulo,
                                  @NotBlank
                                  String mensaje,
                                  @NotNull
                                  Long usuario_id,
                                  @NotNull
                                  Long curso_id
                                  ) {
}
