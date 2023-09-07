package com.alura.latam.foroalura.domain.usuario.validaciones;

import com.alura.latam.foroalura.domain.usuario.DatosRegistroUsuario;

public class CorreoExistente {
    public void validar(DatosRegistroUsuario datos){
        var correo = datos.email();

    }
}
