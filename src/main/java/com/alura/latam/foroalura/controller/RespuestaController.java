package com.alura.latam.foroalura.controller;

import com.alura.latam.foroalura.domain.curso.DatosRespuestaCurso;
import com.alura.latam.foroalura.domain.respuesta.*;
import com.alura.latam.foroalura.domain.topic.DatosActualizarTopico;
import com.alura.latam.foroalura.domain.topic.DatosRespuestaTopico;
import com.alura.latam.foroalura.domain.topic.Topico;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    // Se agrega la anotación @Autowired para que Spring inyecte la dependencia de CursoRepository
    @Autowired
    private ResponderService service;

    // Se agrega la anotación @Autowired para que Spring inyecte la dependencia de CursoRepository
    @Autowired
    private RespuestaRepository repository;

    /* Se agrega la anotación @PostMapping para que Spring sepa que este método se ejecuta cuando se hace una petición POST a la ruta /respuestas
     El método recibe como parámetro un objeto de tipo DatosRegistroCurso, que es el que se recibe en el cuerpo de la petición
     Ademas este tendra que validar los datos segun las anotaciones de validación que se le agregaron a la clase DatosRegistroCurso
     Finalmente debe retornar un objeto de tipo ResponseEntity<DatosRespuestaRespuesta> que es el que se va a enviar como respuesta */
    @PostMapping
    public ResponseEntity<DatosRespuestaRespuesta> responder (@RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta){
        return service.responder(datosRegistroRespuesta);
    }


    /* El metodo Get lista las respuestas creados en la base de datos, se configura una paginanzion y un tamaño por pagina
     * y si la request es correcta retorna un 200 ok con la lista de  cursos*/
    @GetMapping
    public ResponseEntity<Page<DatosRespuestaRespuesta>> listarRespuestas(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(repository.findAll(paginacion).map(DatosRespuestaRespuesta::new));
    }


    /*El metodo Put actualiza los datos de una respuesta, recibe como parametro un objeto de tipo DatosActualizarRespuesta
     * que es el que se recibe en el cuerpo de la petición, ademas este tendra que validar los datos segun las anotaciones de validación
     * que se le agregaron a la clase DatosActualizarRespuesta, finalmente debe retornar un objeto de tipo ResponseEntity<DatosRespuestaRespuesta>*/
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaRespuesta> actualizarRespuesta(@RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta, @PathVariable Long id){
        if (repository.existsByContenido(datosActualizarRespuesta.contenido())){
            throw new IllegalArgumentException("Esta respuesta ya existe");
        }else {
            Respuesta respuesta = repository.getReferenceById(id);
            respuesta.actualizarDatos(datosActualizarRespuesta);
            DatosRespuestaRespuesta datosRespuestaRespuesta = new DatosRespuestaRespuesta(respuesta);
            return ResponseEntity.ok(datosRespuestaRespuesta);
        }
    }


    /*El metodo Delete elimina una respuesta, recibe como parametro el id de la respuesta que se quiere eliminar por medio de la
     URL y retorna un 204 no content si la operación fue exitosa, esto por medio del repository de las respuestas que
     contiene el metodo deleteById que recibe como parametro el id de la respuesta que se quiere eliminar
     * */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    /* El metodo Get obtiene los datos de una respuesta, recibe como parametro el id de la respuesta que se quiere obtener por
     *medio de la URL y retorna un 200 ok con los datos de la respuesta, esto por medio del repository de las respuestas que
     * contiene el metodo getReferenceById que recibe como parametro el id de la respuesta que se quiere obtener */
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaRespuesta> obtenerRespuesta(@PathVariable Long id){
        Respuesta respuesta = repository.getReferenceById(id);
        DatosRespuestaRespuesta datosRespuestaRespuesta = new DatosRespuestaRespuesta(respuesta);
        return ResponseEntity.ok(datosRespuestaRespuesta);
    }

}
