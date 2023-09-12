package com.alura.latam.foroalura.controller;

import com.alura.latam.foroalura.domain.curso.*;
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
@RequestMapping("/curso")
public class CursoController {

    // Se agrega la anotación @Autowired para que Spring inyecte la dependencia de CursoRepository
    @Autowired
    private CursoRepository cursoRepository;

    /*Se agrega la anotación @PostMapping para que Spring sepa que este método se ejecuta cuando se hace una petición POST a la ruta /curso
    El método recibe como parámetro un objeto de tipo DatosRegistroCurso, que es el que se recibe en el cuerpo de la petición
    Ademas este tendra que validar los datos segun las anotaciones de validación que se le agregaron a la clase DatosRegistroCurso
    Finalmente debe retornar un objeto de tipo ResponseEntity<DatosRespuestaCurso> que es el que se va a enviar como respuesta */

    @PostMapping
    public ResponseEntity<DatosRespuestaCurso> crearCurso(@RequestBody @Valid DatosRegistroCurso datosRegistroCurso){
        Curso curso = new Curso(datosRegistroCurso);
        cursoRepository.save(curso);
        DatosRespuestaCurso datosRespuestaCurso = new DatosRespuestaCurso(curso.getId(), curso.getNombre(), curso.getDescripcion(), curso.getDuracion(), curso.getLenguaje());
        return ResponseEntity.ok(datosRespuestaCurso);
    }

    /* El metodo Get lista los cursos creados en la base de datos, se configura una paginanzion y un tamaño por pagina
    * y si la request es correcta retorna un 200 ok con la lista de  cursos*/
    @GetMapping
    public ResponseEntity<Page<DatosRespuestaCurso>> listadoCursos(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(cursoRepository.findAll(paginacion).map(DatosRespuestaCurso::new));
    }


    /*El metodo Put actualiza los datos de un curso, recibe como parametro un objeto de tipo DatosActualizarCurso
    * que es el que se recibe en el cuerpo de la petición, ademas este tendra que validar los datos segun las anotaciones de validación
    * que se le agregaron a la clase DatosActualizarCurso, finalmente debe retornar un objeto de tipo ResponseEntity<DatosRespuestaCurso>*/
    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaCurso> editarCurso(@RequestBody @Valid DatosActualizarCurso datosActualizarCurso){
        Curso curso = cursoRepository.getReferenceById(datosActualizarCurso.id());
        curso.actualizarDatos(datosActualizarCurso);
        DatosRespuestaCurso datosRespuestaCurso = new DatosRespuestaCurso(curso.getId(), curso.getNombre(), curso.getDescripcion(), curso.getDuracion(), curso.getLenguaje());
        return ResponseEntity.ok(datosRespuestaCurso);
    }


    /*El metodo Delete elimina un curso, recibe como parametro el id del curso que se quiere eliminar por medio de la
    URL y retorna un 204 no content si la operación fue exitosa, esto por medio del repository de los cursos que
    contiene el metodo deleteById que recibe como parametro el id del curso que se quiere eliminar
    * */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarCurso(@PathVariable Long id){
        cursoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    /* El metodo Get obtiene los datos de un curso, recibe como parametro el id del curso que se quiere obtener por
     *medio de la URL y retorna un 200 ok con los datos del curso, esto por medio del repository de los cursos que
     * contiene el metodo getReferenceById que recibe como parametro el id del curso que se quiere obtener */
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaCurso> obtenerCurso(@PathVariable Long id){
        Curso curso = cursoRepository.getReferenceById(id);
        DatosRespuestaCurso datosRespuestaCurso = new DatosRespuestaCurso(curso.getId(), curso.getNombre(), curso.getDescripcion(), curso.getDuracion(), curso.getLenguaje());
        return ResponseEntity.ok(datosRespuestaCurso);
    }

    /* El metodo Get obtiene los datos de un curso, recibe como parametro el lenguaje del curso que se quiere obtener por
     *medio de la URL y retorna un 200 ok con los datos del curso, esto por medio del repository de los cursos que
     * contiene el metodo getAllByLenguaje que recibe como parametro el lenguaje del curso que se quiere obtener */
    @GetMapping("/lenguaje/{lenguaje}")
    public ResponseEntity<List<Curso>> obtenerCursoPorLenguaje(@PathVariable Lenguaje lenguaje){
        List<Curso> cursos = cursoRepository.getAllByLenguaje(lenguaje);
        return ResponseEntity.ok(cursos);
    }
}
