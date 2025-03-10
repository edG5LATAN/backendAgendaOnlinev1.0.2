package com.agenda.agenda.domain.model;

import com.agenda.agenda.domain.dto.contacto.DtoContacto;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "contacto")
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_contacto;
    private String contacto;
    private String imagen;
    private String telefono;
    @Embedded
    private Direccion direccion;
    @Embedded
    private RedesSociales redes;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;


    //constructor
    public Contacto() {
    }
    public Contacto(Long id_contacto, Usuario usuario, String contacto, String telefono, Direccion direccion, RedesSociales redes,String imagen) {
        this.id_contacto = id_contacto;
        this.usuario = usuario;
        this.contacto = contacto;
        this.telefono = telefono;
        this.direccion = direccion;
        this.redes = redes;
        this.imagen= imagen;
    }
    public void actualizar(DtoContacto dtoContacto) {
        if(dtoContacto.contacto()!=null){
            this.contacto= dtoContacto.contacto();
        }
        if(dtoContacto.telefono()!=null){
            this.telefono= dtoContacto.telefono();
        }
        if(dtoContacto.direccion()!=null){
            this.direccion= dtoContacto.direccion();
        }
        if(dtoContacto.redes()!=null){
            this.redes=dtoContacto.redes();
        }
        if(dtoContacto.imagen()!=null){
            this.imagen=dtoContacto.imagen();
        }
    }

    public Contacto(DtoContacto dtoContacto,Usuario usuario) {
        this.contacto= dtoContacto.contacto();
        this.telefono= dtoContacto.telefono();
        this.direccion=dtoContacto.direccion();
        this.redes=dtoContacto.redes();
        this.imagen= dtoContacto.imagen();
        this.usuario=usuario;
    }

    //getter and setter
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public RedesSociales getRedes() {
        return redes;
    }

    public void setRedes(RedesSociales redes) {
        this.redes = redes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId_contacto() {
        return id_contacto;
    }

    public void setId_contacto(Long id_contacto) {
        this.id_contacto = id_contacto;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

}
