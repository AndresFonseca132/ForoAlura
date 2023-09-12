package com.alura.latam.foroalura.controller;

import com.alura.latam.foroalura.domain.usuario.DatosAutenticacionUsuario;
import com.alura.latam.foroalura.domain.usuario.Usuario;
import com.alura.latam.foroalura.infra.security.DatosJWTToken;
import com.alura.latam.foroalura.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    // Se agrega la anotación @Autowired para que Spring inyecte la dependencia de CursoRepository
    @Autowired
    private AuthenticationManager authenticationManager;

    // Se agrega la anotación @Autowired para que Spring inyecte la dependencia de CursoRepository
    @Autowired
    private TokenService tokenService;

    /* Se agrega la anotación @PostMapping para que Spring sepa que este método se ejecuta cuando se hace una petición POST a la ruta /login
     El método recibe como parámetro un objeto de tipo DatosAutenticacionUsuario, que es el que se recibe en el cuerpo de la petición
     Ademas este tendra que validar los datos segun las anotaciones de validación que se le agregaron a la clase DatosAutenticacionUsuario
     Finalmente debe retornar un objeto de tipo ResponseEntity<DatosJWTToken> que es el que se va a enviar como respuesta,
      este sera el token con el que se podra autenticar el usuario*/
    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){
        Authentication authtoken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.usuario(), datosAutenticacionUsuario.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authtoken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
