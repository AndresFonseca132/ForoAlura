package com.alura.latam.foroalura.controller;

import com.alura.latam.foroalura.domain.usuario.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registroUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario){
        if (usuarioRepository.existsByEmailOrUsuario(datosRegistroUsuario.email(), datosRegistroUsuario.usuario())){
            return ResponseEntity.badRequest().build();
        }else {
            Usuario usuario = new Usuario(datosRegistroUsuario);
            usuarioRepository.save(usuario);
            DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getTelefono(), usuario.getUsuario());
            return ResponseEntity.ok(datosRespuestaUsuario);
        }
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuario>> listadoUsuarios(@PageableDefault(size = 5) Pageable paginacion){
        //return UsuarioRepository.findAll(paginacion).map(DatosListadoUsuario::new);
        return ResponseEntity.ok(usuarioRepository.findByActivoTrue(paginacion).map(DatosListadoUsuario::new));
    }



}
