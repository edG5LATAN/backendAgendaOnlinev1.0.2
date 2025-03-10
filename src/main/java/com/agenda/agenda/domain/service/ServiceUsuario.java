package com.agenda.agenda.domain.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.agenda.agenda.domain.dto.usuario.DtoMostrarUsuario;
import com.agenda.agenda.domain.dto.usuario.DtoUsuario;
import com.agenda.agenda.domain.model.Usuario;
import com.agenda.agenda.domain.repository.RepositoryUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class ServiceUsuario {

    @Autowired
    private RepositoryUsuario repositoryUsuario;

    public ResponseEntity mostrar() {
        var usuarios = repositoryUsuario.findAll().stream().map(DtoMostrarUsuario::new).toList();
        return ResponseEntity.ok(usuarios);
    }

    public ResponseEntity borrar(Long id) {
        var usuario= repositoryUsuario.findById(id);
        if(usuario.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            repositoryUsuario.delete(usuario.get());
            return ResponseEntity.noContent().build();
        }
    }

    public ResponseEntity unidad(Long id) {
        var usuario= repositoryUsuario.findById(id);
        if(usuario.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(new DtoMostrarUsuario(usuario.get()));
        }
    }

    public ResponseEntity crear(@Valid DtoUsuario dtoUsuario, UriComponentsBuilder uriComponentsBuilder) {
         var clave= convertirClave(dtoUsuario.clave());
         var usuario= repositoryUsuario.save(new Usuario(clave,dtoUsuario));
        URI url= uriComponentsBuilder.path("v1/usuario/unidad/{id}").buildAndExpand(usuario.getId_usuario()).toUri();
        return ResponseEntity.created(url).body(new DtoMostrarUsuario(usuario));

    }

    public String convertirClave(String clave){
        return BCrypt.withDefaults().hashToString(12, clave.toCharArray());
    }
}
