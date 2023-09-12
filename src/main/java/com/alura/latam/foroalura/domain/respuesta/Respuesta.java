package com.alura.latam.foroalura.domain.respuesta;

import com.alura.latam.foroalura.domain.topic.Topico;
import com.alura.latam.foroalura.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// Se agregan las anotaciones necesarias para que Spring sepa que esta clase es una entidad de la base de datos
// Ademas dentro de esta clase se confiugran los atributos y metodos de la entidad.
@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String contenido;
    LocalDateTime fechaCreacion = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "topico_id")
    Topico topico;

    public Respuesta(DatosRegistroRespuesta datosRegistroRespuesta, Usuario usuario, Topico topico) {
        this.contenido = datosRegistroRespuesta.contenido();
        this.usuario = usuario;
        this.topico = topico;
    }

    public void actualizarDatos(DatosActualizarRespuesta datosActualizarRespuesta) {
        if (datosActualizarRespuesta.contenido() != null) {
            this.contenido = datosActualizarRespuesta.contenido();
        }
        this.fechaCreacion = LocalDateTime.now();
    }
}
