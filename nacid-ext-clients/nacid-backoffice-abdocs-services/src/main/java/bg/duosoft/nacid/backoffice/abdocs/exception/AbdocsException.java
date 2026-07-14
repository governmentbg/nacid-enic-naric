package bg.duosoft.nacid.backoffice.abdocs.exception;

public class AbdocsException extends RuntimeException {

    public AbdocsException() {
        super();
    }

    public AbdocsException(String message) {
        super(message);
    }

    public AbdocsException(String message, Throwable cause) {
        super(message, cause);
    }
}