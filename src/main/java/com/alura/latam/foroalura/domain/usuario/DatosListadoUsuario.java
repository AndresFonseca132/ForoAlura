package com.alura.latam.foroalura.domain.usuario;

public record DatosListadoUsuario(Long id, String nombre, String email, String telefono, String usuario) {
    public DatosListadoUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getTelefono(), usuario.getUsuario());
    }
}
