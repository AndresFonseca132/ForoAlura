package com.alura.latam.foroalura.domain.topic;

import org.springframework.data.jpa.repository.JpaRepository;

// La intergace TopicoRepository extiende de JpaRepository, que es una interfaz de Spring Data JPA que
// nos permite realizar operaciones CRUD sobre la entidad Topico.
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Boolean existsByTituloOrMensaje(String titulo, String mensaje);
}
