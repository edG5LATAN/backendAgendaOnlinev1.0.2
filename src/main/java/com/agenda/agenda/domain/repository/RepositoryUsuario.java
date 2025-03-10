package com.agenda.agenda.domain.repository;


import com.agenda.agenda.domain.model.Usuario;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositoryUsuario extends JpaRepository<Usuario,Long> {
    Usuario findByUser(@NotNull String usuario);

    Usuario findByCorreo(String username);
}
