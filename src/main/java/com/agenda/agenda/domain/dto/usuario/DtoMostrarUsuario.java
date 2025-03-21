package com.agenda.agenda.domain.dto.usuario;

import com.agenda.agenda.domain.model.Usuario;

public record DtoMostrarUsuario(
       Long id_usuario,
       String user,
       String telefono,
       String  correo,
       String direccion,
       String imagen
) {
    public DtoMostrarUsuario(Usuario usuario){
        this(usuario.getId_usuario(), usuario.getUser(), usuario.getTelefono(),usuario.getCorreo(), usuario.getDireccion(), usuario.getImagen());
    }
}
