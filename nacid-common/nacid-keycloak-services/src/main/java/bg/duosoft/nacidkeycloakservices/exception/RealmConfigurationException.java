package bg.duosoft.nacidkeycloakservices.exception;

public class RealmConfigurationException extends RuntimeException {

    public RealmConfigurationException() {
        super();
    }

    public RealmConfigurationException(String message) {
        super(message);
    }

    public RealmConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RealmConfigurationException(Throwable cause) {
        super(cause);
    }

    protected RealmConfigurationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
