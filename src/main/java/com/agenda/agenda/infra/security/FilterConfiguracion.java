package com.agenda.agenda.infra.security;


import com.agenda.agenda.domain.repository.RepositoryUsuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FilterConfiguracion extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private RepositoryUsuario repositoryUsuario;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var auth= request.getHeader("Authorization");
        if(auth!=null){
            var token= auth.replace("Bearer ","");
            if(token!=null){
                var subject= tokenService.getSubeject(token);
                var usuario= repositoryUsuario.findByCorreo(subject);
                var autenticar= new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(autenticar);
            }
        }

        filterChain.doFilter(request,response);
    }
}
