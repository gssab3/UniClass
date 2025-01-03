package it.unisa.uniclass.common.exceptions;

public class AlreadyExistentUserException extends Exception {
    public AlreadyExistentUserException(String message) {
        super(message);
    }

    public AlreadyExistentUserException() {super();}
}
