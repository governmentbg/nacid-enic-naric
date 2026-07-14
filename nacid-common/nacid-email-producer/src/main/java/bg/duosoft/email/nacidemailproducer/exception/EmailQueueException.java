package bg.duosoft.email.nacidemailproducer.exception;

public class EmailQueueException extends RuntimeException {

    public EmailQueueException() {
        super();
    }

    public EmailQueueException(String message) {
        super(message);
    }

    public EmailQueueException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailQueueException(Throwable cause) {
        super(cause);
    }

    protected EmailQueueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
