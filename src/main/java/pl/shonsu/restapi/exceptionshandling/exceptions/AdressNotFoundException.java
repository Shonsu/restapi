package pl.shonsu.restapi.exceptionshandling.exceptions;

import javax.persistence.EntityNotFoundException;

public class AdressNotFoundException extends EntityNotFoundException {

    public AdressNotFoundException() {
    }

    public AdressNotFoundException(String message) {
        super(message);
    }

    public AdressNotFoundException(Long id) {
        super("Can't find adress by given id " + id);
    }
}
