package com.alura.latam.foroalura.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
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
}
