package bg.duosoft.nacid.backoffice.abdocs.config.security.retryer;

import feign.RetryableException;
import feign.Retryer;

public class FeignRequestRetryer implements Retryer {

    private final int maxAttempts;
    private final long backoff;
    int attempt;

    public FeignRequestRetryer() {
        this(50, 1);
    }

    public FeignRequestRetryer(long backoff, int maxAttempts) {
        this.backoff = backoff;
        this.maxAttempts = maxAttempts;
        this.attempt = 1;
    }

    public void continueOrPropagate(RetryableException e) {
        if (attempt++ > maxAttempts) {
            throw e;
        }

        try {
            Thread.sleep(backoff);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public Retryer clone() {
        return new FeignRequestRetryer(backoff, maxAttempts);
    }
}