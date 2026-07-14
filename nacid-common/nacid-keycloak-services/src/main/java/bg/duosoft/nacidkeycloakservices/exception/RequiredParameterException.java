package bg.duosoft.nacidkeycloakservices.exception;

public class RequiredParameterException extends RuntimeException {

    public RequiredParameterException() {
        super();
    }

    public RequiredParameterException(String message) {
        super(message);
    }

    public RequiredParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequiredParameterException(Throwable cause) {
        super(cause);
    }

    protected RequiredParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
