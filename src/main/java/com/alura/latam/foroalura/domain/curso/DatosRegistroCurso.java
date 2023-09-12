package com.alura.latam.foroalura.domain.curso;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// DTO de registro de curso, recibe los datos del formulario de registro de curso
public record DatosRegistroCurso(@NotBlank
                                 String nombre,
                                 @NotBlank
                                 String descripcion,
                                 @NotBlank
                                 String duracion,
                                 @NotNull
                                 Lenguaje lenguaje) {
}
