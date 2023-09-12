package com.alura.latam.foroalura.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// Este es una clase record que lo que hace es contener los parametros que se van a recibir en el body de la peticion
// Para asi poder crear un nuevo topico
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
