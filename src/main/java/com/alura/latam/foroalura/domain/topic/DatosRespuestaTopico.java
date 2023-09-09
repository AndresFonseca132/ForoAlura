package com.alura.latam.foroalura.domain.topic;


import java.time.LocalDateTime;

public record DatosRespuestaTopico(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, String estado, Long idUsuario, Long idCurso){
    public DatosRespuestaTopico(Topico topico) {
        this(topico.getId(),topico.getTitulo(),topico.getMensaje(),topico.getFechaCreacion(), String.valueOf(topico.getEstado()),topico.getUsuario().getId(),topico.getCurso().getId());
    }

}
