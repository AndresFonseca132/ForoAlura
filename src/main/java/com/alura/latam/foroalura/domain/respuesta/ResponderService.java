package com.alura.latam.foroalura.domain.respuesta;

import com.alura.latam.foroalura.domain.topic.TopicoRepository;
import com.alura.latam.foroalura.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponderService {

    @Autowired
    private RespuestaRepository repository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

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
