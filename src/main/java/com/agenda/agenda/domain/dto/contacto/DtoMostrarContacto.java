package com.agenda.agenda.domain.dto.contacto;

import com.agenda.agenda.domain.model.Contacto;
import com.agenda.agenda.domain.model.Direccion;
import com.agenda.agenda.domain.model.RedesSociales;

public record DtoMostrarContacto(
        Long id,
        String contacto,
        String imagen,
        String telefono,
        Direccion direccion,
        RedesSociales redes,
        String usuario
) {
    public DtoMostrarContacto(Contacto contacto){
        this(contacto.getId_contacto(), contacto.getContacto(), contacto.getImagen(),
                contacto.getTelefono(), contacto.getDireccion(),
                contacto.getRedes(),contacto.getUsuario().getUser());
    }
}
