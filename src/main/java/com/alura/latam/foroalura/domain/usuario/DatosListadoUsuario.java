package com.alura.latam.foroalura.domain.usuario;

// Esta clase record es un DTO que se utiliza para devolver los datos de un usuario en el listado de usuarios
public record DatosListadoUsuario(Long id, String nombre, String email, String telefono, String usuario) {
    public DatosListadoUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getTelefono(), usuario.getUsuario());
    }
}
