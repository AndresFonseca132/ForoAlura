package com.alura.latam.foroalura.controller;

import com.alura.latam.foroalura.domain.course.*;
import com.alura.latam.foroalura.domain.usuario.DatosRespuestaUsuario;
import com.alura.latam.foroalura.domain.usuario.Usuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaCurso> crearCurso(@RequestBody @Valid DatosRegistroCurso datosRegistroCurso){
        Curso curso = new Curso(datosRegistroCurso);
        cursoRepository.save(curso);
        DatosRespuestaCurso datosRespuestaCurso = new DatosRespuestaCurso(curso.getId(), curso.getNombre(), curso.getDescripcion(), curso.getDuracion(), curso.getLenguaje());
        return ResponseEntity.ok(datosRespuestaCurso);
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listadoCursos(){
        return ResponseEntity.ok(cursoRepository.findAll());
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaCurso> editarCurso(@RequestBody @Valid DatosActualizarCurso datosActualizarCurso){
        Curso curso = cursoRepository.getReferenceById(datosActualizarCurso.id());
        curso.actualizarDatos(datosActualizarCurso);
        DatosRespuestaCurso datosRespuestaCurso = new DatosRespuestaCurso(curso.getId(), curso.getNombre(), curso.getDescripcion(), curso.getDuracion(), curso.getLenguaje());
        return ResponseEntity.ok(datosRespuestaCurso);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarCurso(@PathVariable Long id){
        cursoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaCurso> obtenerCurso(@PathVariable Long id){
        Curso curso = cursoRepository.getReferenceById(id);
        DatosRespuestaCurso datosRespuestaCurso = new DatosRespuestaCurso(curso.getId(), curso.getNombre(), curso.getDescripcion(), curso.getDuracion(), curso.getLenguaje());
        return ResponseEntity.ok(datosRespuestaCurso);
    }

    @GetMapping("/lenguaje/{lenguaje}")
    public ResponseEntity<List<Curso>> obtenerCursoPorLenguaje(@PathVariable Lenguaje lenguaje){
        List<Curso> cursos = cursoRepository.getAllByLenguaje(lenguaje);
        return ResponseEntity.ok(cursos);
    }
}
