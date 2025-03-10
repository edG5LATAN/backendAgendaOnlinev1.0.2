package com.agenda.agenda.domain.dto.usuario;

import com.agenda.agenda.domain.enumerate.Roles;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record DtoUsuario(
        @NotNull @JsonAlias("nombre") String user,
        @NotNull String correo,
        @NotNull String clave,
        @NotNull String imagen,
        @NotNull String telefono,
        @NotNull Roles rol
) {
}
