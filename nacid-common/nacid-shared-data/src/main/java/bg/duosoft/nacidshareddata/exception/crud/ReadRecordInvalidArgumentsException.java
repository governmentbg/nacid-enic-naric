package bg.duosoft.nacidshareddata.exception.crud;

public class ReadRecordInvalidArgumentsException extends RuntimeException {

    public ReadRecordInvalidArgumentsException() {
        super();
    }

    public ReadRecordInvalidArgumentsException(String message) {
        super(message);
    }
}