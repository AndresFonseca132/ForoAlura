package com.alura.latam.foroalura.domain.curso;

import com.alura.latam.foroalura.domain.respuesta.Respuesta;
import jakarta.validation.constraints.NotNull;

// Este DTO contiene los datos que se le retornaran al cliente cuando realice alguna peticion ya sea POST, GET o PUT
// Ademas se crera un constructor que reciba un objeto de tipo Curso y se encargara de extraer los datos necesarios
public record DatosRespuestaCurso(Long id, String nombre, String descripcion, String duracion, Lenguaje lenguaje) {

        public DatosRespuestaCurso(@NotNull Curso curso) {
            this(curso.getId(), curso.getNombre(), curso.getDescripcion(), curso.getDuracion(), curso.getLenguaje());
        }
}
