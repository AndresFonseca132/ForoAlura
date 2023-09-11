package com.alura.latam.foroalura.domain.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Page<Usuario> findByActivoTrue(Pageable paginacion);

    Boolean existsByEmailOrUsuario(String email, String usuario);

    UserDetails findByUsuario(String usuario);

    //Boolean findByActivoTrue(Long idUsuario);
}
