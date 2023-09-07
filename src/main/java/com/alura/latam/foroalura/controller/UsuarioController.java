package com.alura.latam.foroalura.controller;

import com.alura.latam.foroalura.domain.usuario.DatosRegistroUsuario;
import com.alura.latam.foroalura.domain.usuario.Usuario;
import com.alura.latam.foroalura.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public String RegistrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario){
        try{
            System.out.println(datosRegistroUsuario.nombre());
            Usuario usuario = usuarioRepository.save(new Usuario(datosRegistroUsuario));
            return "El usuario se ha registrado con exito";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Email o usuario ya existen";
        }
    }
}
