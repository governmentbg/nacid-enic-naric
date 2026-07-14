package bg.duosoft.nacidbackofficeshareddata.exception;

public class AppTypeNotPresentedException extends RuntimeException {

    public AppTypeNotPresentedException() {
        super();
    }

    public AppTypeNotPresentedException(String message) {
        super(message);
    }

    public AppTypeNotPresentedException(String message, Throwable cause) {
        super(message, cause);
    }
}