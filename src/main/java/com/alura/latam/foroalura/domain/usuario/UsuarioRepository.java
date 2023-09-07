package com.alura.latam.foroalura.domain.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Page<Usuario> findByActivoTrue(Pageable paginacion);

    Boolean existsByEmailOrUsuario(String email, String usuario);

    //Boolean findByActivoTrue(Long idUsuario);
}
