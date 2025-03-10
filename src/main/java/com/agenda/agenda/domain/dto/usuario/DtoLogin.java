package com.agenda.agenda.domain.dto.usuario;

import jakarta.validation.constraints.NotNull;

public record DtoLogin(
        @NotNull String correo,
        @NotNull String clave
) {
}
