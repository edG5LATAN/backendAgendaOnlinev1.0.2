package com.agenda.agenda.domain.repository;


import com.agenda.agenda.domain.model.Contacto;
import com.agenda.agenda.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryContacto extends JpaRepository<Contacto,Long> {

    @Query(value = "SELECT c FROM Contacto c WHERE c.usuario = :usuario")
    List<Contacto> buscarPorUsuario(Optional<Usuario> usuario);

    @Query("SELECT c FROM Contacto c WHERE c.contacto LIKE %:nombre%")
    List<Contacto> buscarContactoPorNombre(String nombre);
}

