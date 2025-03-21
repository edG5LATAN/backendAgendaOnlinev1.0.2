package com.agenda.agenda.controller;


import com.agenda.agenda.domain.dto.usuario.DtoLogin;
import com.agenda.agenda.domain.model.Usuario;
import com.agenda.agenda.infra.security.TokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
@RequestMapping("v1/login")
public class ControllerLogin {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/loguearse")
    public ResponseEntity loguearse(@RequestBody @Valid DtoLogin dtoLogin, HttpServletResponse response){
        Authentication authen= new UsernamePasswordAuthenticationToken(dtoLogin.correo(),dtoLogin.clave());
        var usuario= authenticationManager.authenticate(authen);
        var token = tokenService.getToken((Usuario) usuario.getPrincipal());

        Cookie cookie=new Cookie("JWT",token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(1800);

        response.addCookie(cookie);

        return ResponseEntity.ok(((Usuario) usuario.getPrincipal()).getId_usuario());
    }

    @PostMapping("logout")
    public ResponseEntity logout(HttpServletResponse response){
        Cookie cookie=new Cookie("JWT","");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);

        return ResponseEntity.ok("logout exitoso");
    }


}
