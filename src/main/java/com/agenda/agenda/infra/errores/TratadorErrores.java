package com.agenda.agenda.infra.errores;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class TratadorErrores {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity errorNoEncontrado(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("tipo de variable invalida");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity valoresNulos(MethodArgumentNotValidException error){
        var errores= error.getFieldErrors().stream().map(DtoErrores::new).toList();
        return ResponseEntity.ok(errores);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity duplicadoInfo(DataIntegrityViolationException dataerror){
        return ResponseEntity.badRequest().body("Error de sql repetido.");
    }

}
