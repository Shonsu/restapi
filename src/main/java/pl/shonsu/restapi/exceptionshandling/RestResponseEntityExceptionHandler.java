package pl.shonsu.restapi.exceptionshandling;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.shonsu.restapi.exceptionshandling.exceptions.AdressNotFoundException;
import pl.shonsu.restapi.exceptionshandling.exceptions.PersonNotFoundException;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.*;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleContraintViolationException(ConstraintViolationException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionMessage(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.toString(),
                        getValidationsErrors(ex)));
    }
    @ExceptionHandler(value = {PersonNotFoundException.class, AdressNotFoundException.class})
    public ResponseEntity<Object> resourceNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionMessage(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.toString(),
                        ex.getMessage()));
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                 HttpHeaders headers,
                                 HttpStatus status, WebRequest request) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionMessage(
                        LocalDateTime.now(),
                        status.toString(),
                        getValidationsErrors(ex)));
    }

    private static List<ValidationError> getValidationsErrors(Exception ex) {
        if(ex instanceof MethodArgumentNotValidException ex1){
            return ex1.getBindingResult().getFieldErrors().stream()
                    .map(err->new ValidationError(err.getField(), err.getDefaultMessage()))
                    .toList();
        }
        if(ex instanceof ConstraintViolationException ex1){
            return ex1.getConstraintViolations().stream()
                    .map(err->new ValidationError(err.getPropertyPath().toString(), err.getMessage()))
                    .toList();
        }
        return null;
    }
}
