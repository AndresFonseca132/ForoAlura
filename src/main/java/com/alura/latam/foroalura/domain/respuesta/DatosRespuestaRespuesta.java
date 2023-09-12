package com.alura.latam.foroalura.domain.respuesta;


// Clase record que contiene los datos de una respuesta que se le mostrarán al usuario, ademas s contiene un constructor
// que recibe una respuesta y crea un objeto DatosRespuestaRespuesta
public record DatosRespuestaRespuesta(Long id,
                                      String contenido,
                                      Long usuario_id,
                                      Long topico_id) {
    public DatosRespuestaRespuesta(Respuesta respuesta){
        this(respuesta.getId(), respuesta.getContenido(), respuesta.getUsuario().getId(), respuesta.getTopico().getId());
    }
}
