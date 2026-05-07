package bg.duosoft.nacidshareddata.exception.crud;

public class DeleteRecordInvalidArgumentsException extends RuntimeException {

    public DeleteRecordInvalidArgumentsException() {
        super();
    }

    public DeleteRecordInvalidArgumentsException(String message) {
        super(message);
    }
}