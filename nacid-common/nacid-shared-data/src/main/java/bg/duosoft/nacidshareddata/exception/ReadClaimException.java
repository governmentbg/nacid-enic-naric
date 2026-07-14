package bg.duosoft.nacidshareddata.exception;


public class ReadClaimException extends RuntimeException {

    public ReadClaimException() {
    }

    public ReadClaimException(String claim, String token) {
        super("Claim: " + claim + " Token: " + token);
    }

}
