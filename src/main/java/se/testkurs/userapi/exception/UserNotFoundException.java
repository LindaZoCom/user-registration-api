package se.testkurs.userapi.exception;

/**
 * Kastas nar en efterfragad anvandare inte hittas.
 * Mappas till HTTP 404 Not Found av GlobalExceptionHandler.
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
