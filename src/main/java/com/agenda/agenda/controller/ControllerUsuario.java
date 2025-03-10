package com.agenda.agenda.controller;


import com.agenda.agenda.domain.dto.usuario.DtoUsuario;
import com.agenda.agenda.domain.service.ServiceUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("v1/usuario")
public class ControllerUsuario {

    @Autowired
    private ServiceUsuario serviceUsuario;

    @GetMapping("/mostrar")
    public ResponseEntity mostrar(){
        return serviceUsuario.mostrar();
    }

    @GetMapping("/unidad/{id}")
    public ResponseEntity unidad(@PathVariable Long id){
        return serviceUsuario.unidad(id);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity borrar(@PathVariable Long id){
        return serviceUsuario.borrar(id);
    }

    @PostMapping("/crear")
    public ResponseEntity crear(@RequestBody @Valid DtoUsuario dtoUsuario, UriComponentsBuilder uriComponentsBuilder){
        return serviceUsuario.crear(dtoUsuario,uriComponentsBuilder);
    }



}
