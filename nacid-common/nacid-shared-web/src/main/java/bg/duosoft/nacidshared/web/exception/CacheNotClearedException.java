package bg.duosoft.nacidshared.web.exception;

public class CacheNotClearedException extends RuntimeException {

    public CacheNotClearedException() {
        super();
    }

    public CacheNotClearedException(String message) {
        super(message);
    }

    public CacheNotClearedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheNotClearedException(Throwable cause) {
        super(cause);
    }

    protected CacheNotClearedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
