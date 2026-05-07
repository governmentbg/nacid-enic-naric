package bg.duosoft.nacidkeycloakservices.exception;

public class RequiredObjectException extends RuntimeException {

    public RequiredObjectException() {
        super();
    }

    public RequiredObjectException(String message) {
        super(message);
    }

    public RequiredObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequiredObjectException(Throwable cause) {
        super(cause);
    }

    protected RequiredObjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
