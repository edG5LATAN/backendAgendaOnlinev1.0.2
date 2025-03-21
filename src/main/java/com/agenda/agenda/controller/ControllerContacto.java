package com.agenda.agenda.controller;


import com.agenda.agenda.domain.dto.contacto.DtoContacto;
import com.agenda.agenda.domain.service.ServiceContacto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("v1/contacto")
@Transactional
public class ControllerContacto {

    @Autowired
    private ServiceContacto serviceContacto;

    @GetMapping("/mostrar")
    public ResponseEntity mostrar(){
        return serviceContacto.mostrar();
    }

    @GetMapping("/unidad/{id}")
    public ResponseEntity unidad(@PathVariable Long id){
        return serviceContacto.unidad(id);
    }

    @PostMapping("/crear")
    public ResponseEntity crear(@RequestBody @Valid DtoContacto dtoContacto, UriComponentsBuilder uriComponentsBuilder){
        return serviceContacto.crear(dtoContacto,uriComponentsBuilder);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity borrar(@PathVariable Long id){
        return serviceContacto.borrar(id);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity actualizar(@RequestBody DtoContacto dtoContacto,@PathVariable Long id){
        return serviceContacto.actualizar(dtoContacto,id);
    }

    @GetMapping("/buscarPorUsuario/{id}")
    public ResponseEntity buscatPorUsuario(@PathVariable Long id){
        return serviceContacto.buscarPorUsuario(id);
    }

    @GetMapping("/buscarPorNombre/{nombre}")
    public ResponseEntity buscarPorNombre(@PathVariable String nombre){
        return serviceContacto.buscarPorNombre(nombre);
    }
}
