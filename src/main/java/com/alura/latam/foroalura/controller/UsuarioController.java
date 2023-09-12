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
@RequestMapping("/usuarios")
public class UsuarioController {

    // Se agrega la anotación @Autowired para que Spring inyecte la dependencia de CursoRepository
    @Autowired
    private UsuarioRepository usuarioRepository;

    /* Se agrega la anotación @PostMapping para que Spring sepa que este método se ejecuta cuando se hace una petición POST a la ruta /usuarios
     El método recibe como parámetro un objeto de tipo DatosRegistroUsuario, que es el que se recibe en el cuerpo de la petición
     Ademas este tendra que validar los datos segun las anotaciones de validación que se le agregaron a la clase DatosRegistroUsuario
     Finalmente debe retornar un objeto de tipo ResponseEntity<DatosRespuestaUsuario> que es el que se va a enviar como respuesta */
    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registroUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario){
        if (usuarioRepository.existsByEmailOrUsuario(datosRegistroUsuario.email(), datosRegistroUsuario.usuario())){
            throw new IllegalArgumentException("El email o el usuario ya existen");
        }else {
            Usuario usuario = new Usuario(datosRegistroUsuario);
            usuarioRepository.save(usuario);
            DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getTelefono(), usuario.getUsuario());
            return ResponseEntity.ok(datosRespuestaUsuario);
        }
    }

    /* El metodo Get lista los usuarios creados en la base de datos, se configura una paginanzion y un tamaño por pagina
     * y si la request es correcta retorna un 200 ok con la lista de usuarios*/
    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuario>> listadoUsuarios(@PageableDefault(size = 5) Pageable paginacion){
        //return UsuarioRepository.findAll(paginacion).map(DatosListadoUsuario::new);
        return ResponseEntity.ok(usuarioRepository.findByActivoTrue(paginacion).map(DatosListadoUsuario::new));
    }

    /*El metodo Put actualiza los datos de un usuario, recibe como parametro un objeto de tipo DatosActualizarUsuario
     * que es el que se recibe en el cuerpo de la petición, ademas este tendra que validar los datos segun las anotaciones de validación
     * que se le agregaron a la clase DatosActualizarUsuario, finalmente debe retornar un objeto de tipo ResponseEntity<DatosRespuestaUsuario>*/
    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> actualizarUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario){
    Usuario usuario = usuarioRepository.getReferenceById(datosActualizarUsuario.id());
    usuario.actualizarDatos(datosActualizarUsuario);
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getTelefono(), usuario.getUsuario());
        return ResponseEntity.ok(datosRespuestaUsuario);
    }


    /*El metodo Delete elimina un usuario, recibe como parametro el id del usuario que se quiere eliminar por medio de la
     URL y retorna un 204 no content si la operación fue exitosa, esto por medio del repository de los usuarios que
     contiene el metodo deleteById que recibe como parametro el id del usuario que se quiere eliminar
     * */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.desactivarUsuario();
        return ResponseEntity.noContent().build();
    }


    /* El metodo Get obtiene los datos de un usuario, recibe como parametro el id del usuario que se quiere obtener por
     *medio de la URL y retorna un 200 ok con los datos del usuario, esto por medio del repository de los usuarios que
     * contiene el metodo getReferenceById que recibe como parametro el id del usuario que se quiere obtener */
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaUsuario> obtenerUsuario(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getTelefono(), usuario.getUsuario());
        return ResponseEntity.ok(datosRespuestaUsuario);
    }

}

