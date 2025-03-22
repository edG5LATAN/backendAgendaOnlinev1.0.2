package com.agenda.agenda.domain.service;

import com.agenda.agenda.domain.dto.contacto.DtoContacto;
import com.agenda.agenda.domain.dto.contacto.DtoMostrarContacto;
import com.agenda.agenda.domain.model.Contacto;
import com.agenda.agenda.domain.model.Usuario;
import com.agenda.agenda.domain.repository.RepositoryContacto;
import com.agenda.agenda.domain.repository.RepositoryUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Comparator;


@Service
public class ServiceContacto {

    @Autowired
    private RepositoryContacto repositoryContacto;
    @Autowired
    private RepositoryUsuario repositoryUsuario;

    public ResponseEntity mostrar() {
        var contacto = repositoryContacto.findAll().stream().map(DtoMostrarContacto::new).toList();
        return ResponseEntity.ok(contacto);
    }

    public ResponseEntity unidad(Long id) {
        var contacto = repositoryContacto.findById(id);
        if(contacto.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(new DtoMostrarContacto(contacto.get()));
        }
    }

    public ResponseEntity crear(DtoContacto dtoContacto, UriComponentsBuilder uriComponentsBuilder) {
        var usuario= repositoryUsuario.findById(dtoContacto.usuario());
        if(usuario==null){
            return ResponseEntity.notFound().build();
        }else {
            var contacto = repositoryContacto.save(new Contacto(dtoContacto,usuario.get()));
            URI url = uriComponentsBuilder.path("v1/contacto/unidad/{id}").buildAndExpand(contacto.getId_contacto()).toUri();
            return ResponseEntity.created(url).body(new DtoMostrarContacto(contacto));
        }
    }

    public ResponseEntity borrar(Long id) {
        var contacto= repositoryContacto.findById(id);
        if(contacto.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            repositoryContacto.delete(contacto.get());
            return ResponseEntity.noContent().build();
        }
    }

    public ResponseEntity actualizar(DtoContacto dtoContacto, Long id) {
        var contacto= repositoryContacto.findById(id);
        if(contacto.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            contacto.get().actualizar(dtoContacto);
            return ResponseEntity.ok(new DtoMostrarContacto(contacto.get()));
        }

    }

    public ResponseEntity buscarPorUsuario(Long id) {
        var usuario= repositoryUsuario.findById(id);
        if(usuario.isEmpty()){
            return ResponseEntity.ok("no se encontro usuario");
        }else {
            var contactos= repositoryContacto.buscarPorUsuario(usuario);
            return ResponseEntity.ok(contactos.stream()
                    .map(DtoMostrarContacto::new)
                    .sorted(Comparator.comparing(DtoMostrarContacto::contacto))
                    .toList());        }
    }


    public ResponseEntity buscarPorNombre(Long nombre) {
        var contacto=repositoryContacto.buscarContactoPorNombre(nombre);
        return ResponseEntity.ok(contacto.stream().map(DtoMostrarContacto::new).toList());
    }
}
