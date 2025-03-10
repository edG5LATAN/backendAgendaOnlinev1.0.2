package com.agenda.agenda.domain.dto.direccion;

import jakarta.validation.constraints.NotNull;

public record DtoDireccion(
        @NotNull String ciudad,
        @NotNull String coloia
) {
}
