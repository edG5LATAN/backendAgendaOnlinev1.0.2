package com.agenda.agenda.domain.dto.contacto;

import com.agenda.agenda.domain.model.Direccion;
import com.agenda.agenda.domain.model.RedesSociales;
import jakarta.validation.constraints.NotNull;

public record DtoContacto(
        @NotNull String contacto,
        @NotNull String telefono,
        @NotNull Direccion direccion,
        @NotNull RedesSociales redes,
        @NotNull Long usuario,
        @NotNull String imagen
        ) {
}
