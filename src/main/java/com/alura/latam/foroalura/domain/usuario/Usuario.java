package com.alura.latam.foroalura.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

// La clase Usuario se encarga de contener los atributos y los metodos de la entidad Usuario, entre estos metodos
// encontramos getters y setters, constructores y metodos de la interfaz UserDetails
@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String usuario;
    private String clave;

    private boolean activo;

    @Enumerated(EnumType.STRING)
    private Rol rol;


    public Usuario(DatosRegistroUsuario datosRegistroUsuario) {
        this.activo = true;
        this.nombre = datosRegistroUsuario.nombre();
        this.email = datosRegistroUsuario.email();
        this.telefono = datosRegistroUsuario.telefono();
        this.usuario = datosRegistroUsuario.usuario();
        this.clave = datosRegistroUsuario.clave();
        this.rol =  Rol.USER;
    }

    public void actualizarDatos(DatosActualizarUsuario datosActualizarUsuario) {
        if (datosActualizarUsuario.nombre() != null) {
            this.nombre = datosActualizarUsuario.nombre();
        }
        if (datosActualizarUsuario.telefono() != null){
            this.telefono = datosActualizarUsuario.telefono();
        }
        if (datosActualizarUsuario.clave() != null) {
            this.clave = datosActualizarUsuario.clave();
        }
    }

    public void desactivarUsuario() {
        this.activo = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
