package se.testkurs.userapi.exception;

/**
 * Kastas nar en anvandare med samma email redan finns i systemet.
 * Mappas till HTTP 409 Conflict av GlobalExceptionHandler.
 */
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
