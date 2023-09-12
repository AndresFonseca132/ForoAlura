package com.alura.latam.foroalura.domain.usuario;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

// Esta clase es un DTO para actualizar los datos de un usuario en la base de datos, para ello realizamos algunas
// Validaciones y especificamos los parametros que se aceptaran esto indica que solo los datos que estan en esta clase
// se podran actualizar en la base de datos
public record DatosActualizarUsuario(@NotNull
                                     Long id,

                                     String nombre,

                                     @Pattern(regexp = "^([0-9]{6,13})$", message = "El telefono no cumple con la estructura adecuada")
                                     String telefono,

                                     @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$", message = "La clave debe ser de al menos 8 caracteres y debe contener al menos una mayuscula, una minuscula y un numero")
                                     String clave){
}
