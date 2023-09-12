package com.alura.latam.foroalura.domain.usuario;

// Este DTO se utiliza para devolver los datos de un usuario en la respuesta de un endpoint
public record DatosRespuestaUsuario(Long id, String nombre, String email, String telefono, String usuario) {
}
