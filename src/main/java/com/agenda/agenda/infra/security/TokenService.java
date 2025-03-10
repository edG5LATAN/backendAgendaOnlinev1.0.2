package com.agenda.agenda.infra.security;

import com.agenda.agenda.domain.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String getToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256("1234");
            return JWT.create()
                    .withIssuer("agenda")
                    .withClaim("id",usuario.getId_usuario())
                    .withSubject(usuario.getCorreo())
                    .withExpiresAt(tiempoExp())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException(exception.toString());
        }
    }

    public String getSubeject(String token){
        DecodedJWT verifier=null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("1234");
            verifier = JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("agenda")
                    // reusable verifier instance
                    .build()
                    .verify(token);

        } catch (JWTVerificationException exception){
            return exception.getMessage();
        }
        if(verifier.getSubject()==null){
            throw new RuntimeException("subject nulo");
        }
        return verifier.getSubject();
    }

    public Instant tiempoExp(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-06:00"));
    }
}
