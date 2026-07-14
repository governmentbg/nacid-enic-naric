package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

/**
 * User: ggeorgiev
 * Date: 02.12.2025
 * Time: 14:03
 */
public enum ServiceType {
    STANDARD("S"), EXPRESS("E");
    private final String code;
    ServiceType(String st) {
        this.code = st;
    }
    public String code() {
        return code;
    }
}
