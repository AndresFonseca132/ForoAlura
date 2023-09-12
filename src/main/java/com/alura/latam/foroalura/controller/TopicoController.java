package com.alura.latam.foroalura.controller;

import com.alura.latam.foroalura.domain.topic.*;
import com.alura.latam.foroalura.domain.usuario.DatosListadoUsuario;
import com.alura.latam.foroalura.domain.usuario.Usuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    // Se agrega la anotación @Autowired para que Spring inyecte la dependencia de CursoRepository
    @Autowired
    private TopicoService service;

    // Se agrega la anotación @Autowired para que Spring inyecte la dependencia de CursoRepository
    @Autowired
    private TopicoRepository topicoRepository;

    /* Se agrega la anotación @PostMapping para que Spring sepa que este método se ejecuta cuando se hace una petición POST a la ruta /topicos
     El método recibe como parámetro un objeto de tipo DatosRegistroTopico, que es el que se recibe en el cuerpo de la petición
     Ademas este tendra que validar los datos segun las anotaciones de validación que se le agregaron a la clase DatosRegistroTopico
     Finalmente debe retornar un objeto de tipo ResponseEntity<DatosRespuestaTopico> que es el que se va a enviar como respuesta */
    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> crearTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico){
        return service.crearTopico(datosRegistroTopico);
    }


    /* El metodo Get lista los topicos creados en la base de datos, se configura una paginanzion y un tamaño por pagina
     * y si la request es correcta retorna un 200 ok con la lista de topicos*/
    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopico>> listadoTopicos(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosRespuestaTopico::new));
    }

    /*El metodo Put actualiza los datos de un topico, recibe como parametro un objeto de tipo DatosActualizarTopico
     * que es el que se recibe en el cuerpo de la petición, ademas este tendra que validar los datos segun las anotaciones de validación
     * que se le agregaron a la clase DatosActualizarTopico, finalmente debe retornar un objeto de tipo ResponseEntity<DatosRespuestaTopico>*/
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> obtenerTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico);
        return ResponseEntity.ok(datosRespuestaTopico);
    }


    /*El metodo Put actualiza los datos de un topico, recibe como parametro un objeto de tipo DatosActualizarTopico
     * que es el que se recibe en el cuerpo de la petición, ademas este tendra que validar los datos segun las anotaciones de validación
     * que se le agregaron a la clase DatosActualizarTopico, finalmente debe retornar un objeto de tipo ResponseEntity<DatosRespuestaTopico>*/
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico, @PathVariable Long id){
        if (topicoRepository.existsByTituloOrMensaje(datosActualizarTopico.titulo(), datosActualizarTopico.mensaje())){
            throw new IllegalArgumentException("El titulo o el mensaje ya existen");
        }else {
            Topico topico = topicoRepository.getReferenceById(id);
            topico.actualizarDatos(datosActualizarTopico);
            DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico);
            return ResponseEntity.ok(datosRespuestaTopico);
        }
    }

    /*El metodo Delete elimina un topico, recibe como parametro el id del topico que se quiere eliminar por medio de la
     URL y retorna un 204 no content si la operación fue exitosa, esto por medio del repository de los topicos que
     contiene el metodo deleteById que recibe como parametro el id del topico que se quiere eliminar
     * */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
