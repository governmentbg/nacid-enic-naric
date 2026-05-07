package bg.duosoft.nacidkeycloakservices.exception;

public class KeycloakServiceException extends RuntimeException {

    public KeycloakServiceException() {
        super();
    }

    public KeycloakServiceException(String message) {
        super(message);
    }

    public KeycloakServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public KeycloakServiceException(Throwable cause) {
        super(cause);
    }

    protected KeycloakServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
