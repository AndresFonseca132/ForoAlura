package com.alura.latam.foroalura.controller;

import com.alura.latam.foroalura.domain.usuario.*;
import jakarta.transaction.Transactional;
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

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> actualizarUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario){
    Usuario usuario = usuarioRepository.getReferenceById(datosActualizarUsuario.id());
    usuario.actualizarDatos(datosActualizarUsuario);
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getTelefono(), usuario.getUsuario());
        return ResponseEntity.ok(datosRespuestaUsuario);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.desactivarUsuario();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaUsuario> obtenerUsuario(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getTelefono(), usuario.getUsuario());
        return ResponseEntity.ok(datosRespuestaUsuario);
    }

}

