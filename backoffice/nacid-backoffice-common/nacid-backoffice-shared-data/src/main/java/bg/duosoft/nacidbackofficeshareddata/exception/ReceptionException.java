package bg.duosoft.nacidbackofficeshareddata.exception;

public class ReceptionException extends RuntimeException {

    public ReceptionException() {
        super();
    }

    public ReceptionException(String message) {
        super(message);
    }

    public ReceptionException(String message, Throwable cause) {
        super(message, cause);
    }
}