package se.testkurs.userapi.exception;

/**
 * Kastas nar ett ogiltigt email-format skickas in.
 * Mappas till HTTP 400 Bad Request av GlobalExceptionHandler.
 */
public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException(String message) {
        super(message);
    }
}
