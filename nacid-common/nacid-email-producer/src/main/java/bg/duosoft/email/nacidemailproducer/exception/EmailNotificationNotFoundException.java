package bg.duosoft.email.nacidemailproducer.exception;

public class EmailNotificationNotFoundException extends RuntimeException {

    public EmailNotificationNotFoundException() {
        super();
    }

    public EmailNotificationNotFoundException(String message) {
        super(message);
    }

    public EmailNotificationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailNotificationNotFoundException(Throwable cause) {
        super(cause);
    }

    protected EmailNotificationNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
