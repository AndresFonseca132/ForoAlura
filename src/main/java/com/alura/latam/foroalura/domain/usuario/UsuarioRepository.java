package com.alura.latam.foroalura.domain.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

// La intefaz de Usuario Repository extiende de JPA Repository, que es una interfaz de Spring Data JPA
// lo que nos permite es poder ejecutar metodos de JPA para asi poder modificar o guardar los datos, ademas en esta clase
// creamos metodos personalizados para poder buscar usuarios por su nombre de usuario o email
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    Page<Usuario> findByActivoTrue(Pageable paginacion);

    Boolean existsByEmailOrUsuario(String email, String usuario);

    UserDetails findByUsuario(String usuario);

    //Boolean findByActivoTrue(Long idUsuario);
}
