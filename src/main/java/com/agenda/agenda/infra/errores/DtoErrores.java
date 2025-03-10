package com.agenda.agenda.infra.errores;

import org.springframework.validation.FieldError;

public record DtoErrores(
        String error,
        String mensaje
) {
    public DtoErrores(FieldError erro){
        this(erro.getField(), erro.getDefaultMessage());
    }
}
