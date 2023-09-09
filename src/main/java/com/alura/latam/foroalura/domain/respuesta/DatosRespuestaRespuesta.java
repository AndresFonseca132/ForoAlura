package com.alura.latam.foroalura.domain.respuesta;

public record DatosRespuestaRespuesta(Long id,
                                      String contenido,
                                      Long usuario_id,
                                      Long topico_id) {
    public DatosRespuestaRespuesta(Respuesta respuesta){
        this(respuesta.getId(), respuesta.getContenido(), respuesta.getUsuario().getId(), respuesta.getTopico().getId());
    }
}
