package pl.shonsu.restapi.exceptionshandling.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends EntityNotFoundException {
    public PersonNotFoundException(String message) {
        super(message);
    }
    public PersonNotFoundException(Long id) {
        super("Can't find person by given id " + id);
    }

}
