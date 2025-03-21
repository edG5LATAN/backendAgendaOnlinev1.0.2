package com.agenda.agenda.infra.security;


import com.agenda.agenda.domain.repository.RepositoryUsuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
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
        Cookie[] cookies=request.getCookies();
        String token=null;
        if(cookies!=null){
            for (Cookie cookie:cookies){
                if("JWT".equals(cookie.getName())){
                    token=cookie.getValue();
                    break;
                }
            }
        }
        if(token!=null){
            try {
              var subject=tokenService.getSubeject(token);
              if(subject!=null){
                  var usuario= repositoryUsuario.findByCorreo(subject);
                  var autenticar= new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
                  SecurityContextHolder.getContext().setAuthentication(autenticar);
              }
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }

        filterChain.doFilter(request,response);
    }
}
