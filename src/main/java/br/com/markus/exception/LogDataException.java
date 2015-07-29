package br.com.markus.exception;

/**
 * Exception Class
 */
public class LogDataException extends Exception {
    public LogDataException() {
    }

    public LogDataException(String message) {
        super(message);
    }

    public LogDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogDataException(Throwable cause) {
        super(cause);
    }
}