package com.alura.latam.foroalura.domain.topic;

import com.alura.latam.foroalura.domain.curso.CursoRepository;
import com.alura.latam.foroalura.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

// La clase TopicoService es la encargada de realizar la logica de negocio, como algunas validaciones antes de poder
// guardar los datos en la base de datos
@Service
public class TopicoService {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public ResponseEntity<DatosRespuestaTopico> crearTopico(DatosRegistroTopico datos){
        if(!usuarioRepository.findById(datos.usuario_id()).isPresent()){
            throw new IllegalArgumentException("El usuario no existe");
        }

        if(!cursoRepository.findById(datos.curso_id()).isPresent()){
            throw new IllegalArgumentException("El curso no existe");
        }

        var usuario = usuarioRepository.findById(datos.usuario_id()).get();

        var curso = cursoRepository.findById(datos.curso_id()).get();

        var topico = new Topico(datos, usuario, curso);
        if (repository.existsByTituloOrMensaje(topico.getTitulo(), topico.getMensaje())){
            throw new IllegalArgumentException("El titulo o el mensaje ya existen");
        }else {
            repository.save(topico);
            return ResponseEntity.ok(new DatosRespuestaTopico(topico));
        }
    }



}
