package bg.duosoft.email.nacidemailproducer.exception;

public class EmailTemplateNotFoundException extends RuntimeException {

    public EmailTemplateNotFoundException() {
        super();
    }

    public EmailTemplateNotFoundException(String message) {
        super(message);
    }

    public EmailTemplateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailTemplateNotFoundException(Throwable cause) {
        super(cause);
    }

    protected EmailTemplateNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
