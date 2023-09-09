package com.alura.latam.foroalura.domain.respuesta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long>{
    Boolean existsByContenido(String contenido);
}
