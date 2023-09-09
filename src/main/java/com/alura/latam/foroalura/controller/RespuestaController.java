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

    @Autowired
    private ResponderService service;

    @Autowired
    private RespuestaRepository repository;

    @PostMapping
    public ResponseEntity<DatosRespuestaRespuesta> responder (@RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta){
        return service.responder(datosRegistroRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaRespuesta>> listarRespuestas(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(repository.findAll(paginacion).map(DatosRespuestaRespuesta::new));
    }

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

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaRespuesta> obtenerRespuesta(@PathVariable Long id){
        Respuesta respuesta = repository.getReferenceById(id);
        DatosRespuestaRespuesta datosRespuestaRespuesta = new DatosRespuestaRespuesta(respuesta);
        return ResponseEntity.ok(datosRespuestaRespuesta);
    }

}
