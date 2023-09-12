package com.alura.latam.foroalura.domain.topic;


import java.time.LocalDateTime;

// La clase DatosRespuestaTopico es una clase que se utiliza para mostrar los datos de un topico, se utiliza en las
// Peticiones GET, POST y PUT para mostrar los datos de un topico, en el caso del Get su funcion va en solo
// mostrar los datos que estan en este record
public record DatosRespuestaTopico(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, String estado, Long idUsuario, Long idCurso){
    public DatosRespuestaTopico(Topico topico) {
        this(topico.getId(),topico.getTitulo(),topico.getMensaje(),topico.getFechaCreacion(), String.valueOf(topico.getEstado()),topico.getUsuario().getId(),topico.getCurso().getId());
    }

}
