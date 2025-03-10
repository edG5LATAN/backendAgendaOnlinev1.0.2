package com.agenda.agenda.domain.model;


import com.agenda.agenda.domain.dto.usuario.DtoUsuario;
import com.agenda.agenda.domain.enumerate.Roles;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;
    private String user;
    private String imagen;
    private String telefono;
    @Email
    @Column(unique = true)
    private String correo;
    private String clave;
    @Enumerated(EnumType.STRING)
    private Roles rol;

    //contructor
    public Usuario() {
    }
    public Usuario(Long id_usuario, String user, String imagen, String telefono,String correo, String clave, Roles rol) {
        this.id_usuario = id_usuario;
        this.user = user;
        this.imagen=imagen;
        this.telefono=telefono;
        this.correo = correo;
        this.clave = clave;
        this.rol = rol;
    }

    public Usuario(String clave, @Valid DtoUsuario dtoUsuario) {
        this.user= dtoUsuario.user();
        this.imagen= dtoUsuario.imagen();
        this.correo= dtoUsuario.correo();
        this.telefono= dtoUsuario.telefono();
        this.clave=clave;
        this.rol=dtoUsuario.rol();
    }
    // user details
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+rol.name()));
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //getter and setter
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

}
