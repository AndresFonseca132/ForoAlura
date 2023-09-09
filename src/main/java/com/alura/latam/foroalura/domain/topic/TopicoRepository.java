package com.alura.latam.foroalura.domain.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Boolean existsByTituloOrMensaje(String titulo, String mensaje);
}
