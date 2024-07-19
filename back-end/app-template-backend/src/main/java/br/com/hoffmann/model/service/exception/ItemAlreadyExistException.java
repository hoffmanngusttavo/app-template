package br.com.hoffmann.model.service.exception;

public class ItemAlreadyExistException extends RuntimeException {
    public ItemAlreadyExistException() {
    }

    public ItemAlreadyExistException(String message) {
        super(message);
    }

    public ItemAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
