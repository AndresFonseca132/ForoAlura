package com.alura.latam.foroalura.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroUsuario(
        @NotBlank(message = "Debes escribir el nombre")
        String nombre,
        @NotBlank(message = "No puede estar vacio el correo")
        @Email(message = "El correo no cumple con la estructura adecuada")
        String email,
        @NotBlank(message = "El telefono no puede estar vacio")
        @Pattern(regexp = "^([0-9]{6,13})$", message = "El telefono no cumple con la estructura adecuada")
        String telefono,
        @NotBlank(message = "Deber escribir un nombre de Usuario")
        String usuario,
        @NotBlank
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$", message = "La clave debe ser de al menos 8 caracteres y debe contener al menos una mayuscula, una minuscula y un numero")
        String clave

) {
}
