package com.alura.latam.foroalura.domain.respuesta;

import com.alura.latam.foroalura.domain.topic.TopicoRepository;
import com.alura.latam.foroalura.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
/* La clase ResponderService se encarga de realizar varias validaciones antes de agregar las respuestas a la base de
* datos, estre las validaciones que hace encontramos validaciones de existencia de usuarios y topicos y una regla de
* negocio en donde el mismo contenido no exista mas de una vez*/
@Service
public class ResponderService {

    // Se inyectan los repositorios de respuesta, topico y usuario
    @Autowired
    private RespuestaRepository repository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Se crea el metodo responder que recibe un objeto de tipo DatosRegistroRespuesta y retorna un objeto de tipo
    // ResponseEntity<DatosRespuestaRespuesta> que contiene la respuesta que se guardo en la base de datos

    public ResponseEntity<DatosRespuestaRespuesta> responder(DatosRegistroRespuesta datosRegistroRespuesta){
        if(!usuarioRepository.findById(datosRegistroRespuesta.usuario_id()).isPresent()){
            throw new IllegalArgumentException("El usuario no existe");
        }

        if(!topicoRepository.findById(datosRegistroRespuesta.topico_id()).isPresent()){
            throw new IllegalArgumentException("El topico no existe");
        }

        var usuario = usuarioRepository.findById(datosRegistroRespuesta.usuario_id()).get();

        var topico = topicoRepository.findById(datosRegistroRespuesta.topico_id()).get();

        var respuesta = new Respuesta(datosRegistroRespuesta, usuario, topico);
        if (repository.existsByContenido(respuesta.getContenido())){
            throw new IllegalArgumentException("El contenido ya existe");
        }else {
            repository.save(respuesta);
            return ResponseEntity.ok(new DatosRespuestaRespuesta(respuesta));
        }
    }


}
