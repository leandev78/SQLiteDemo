package com.miempresa.tiendaonline.tiendaonline.exception;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLNonTransientConnectionException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Excepciones de Hibernate por conexión JDBC
    @ExceptionHandler(JDBCConnectionException.class)
    public ResponseEntity<String> handleJDBCConnectionException(JDBCConnectionException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("No se pudo conectar con la base de datos. Intente más tarde.");
    }

    // Excepciones de bajo nivel de conexión SQL
    @ExceptionHandler(SQLNonTransientConnectionException.class)
    public ResponseEntity<String> handleSQLNonTransientConnection(SQLNonTransientConnectionException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Error de conexión con la base de datos.");
    }

    // Excepciones generales de acceso a datos
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccess(DataAccessException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Problemas al acceder a la base de datos.");
    }

    // Otras excepciones genéricas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ha ocurrido un error inesperado.");
    }
}
