package com.agenda.agenda.infra.security;


import com.agenda.agenda.domain.repository.RepositoryUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AtutenticacionService implements UserDetailsService {

    @Autowired
    private RepositoryUsuario repositoryUsuario;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repositoryUsuario.findByCorreo(username);
    }
}
