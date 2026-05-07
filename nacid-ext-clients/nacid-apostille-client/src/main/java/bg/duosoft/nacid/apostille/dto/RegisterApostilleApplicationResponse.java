package bg.duosoft.nacid.apostille.dto;

/**
 * User: Georgi
 * Date: 1.4.2020 г.
 * Time: 13:00
 */
public class RegisterApostilleApplicationResponse {
    private int status;
    private String[] errors;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String[] getErrors() {
        return errors;
    }

    public void setErrors(String[] errors) {
        this.errors = errors;
    }
}
