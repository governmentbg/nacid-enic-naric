package bg.duosoft.nacidshareddata.exception;

/**
 * All exceptions thrown during report generation should be based on ReportException
 */
public class ReportException extends RuntimeException {

    public ReportException(String s){
        super(s);
    }

    public ReportException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
