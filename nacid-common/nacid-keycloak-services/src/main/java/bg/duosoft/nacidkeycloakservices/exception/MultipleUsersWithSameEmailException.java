
package bg.duosoft.nacidkeycloakservices.exception;

public class MultipleUsersWithSameEmailException extends RuntimeException {

    public MultipleUsersWithSameEmailException() {
        super();
    }

    public MultipleUsersWithSameEmailException(String message) {
        super(message);
    }

    public MultipleUsersWithSameEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public MultipleUsersWithSameEmailException(Throwable cause) {
        super(cause);
    }

    protected MultipleUsersWithSameEmailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
