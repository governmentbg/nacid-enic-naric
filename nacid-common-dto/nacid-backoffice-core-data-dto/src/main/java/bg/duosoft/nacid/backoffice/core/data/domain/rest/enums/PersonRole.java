package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

public enum PersonRole {
    APPLICANT("APPLICANT"),
    DIPLOMA_OWNER("DIPLOMA_OWNER"),
    REPRESENTATIVE("REPRESENTATIVE"),
    REPRESENTATIVE_COMPANY("REPRESENTATIVE_COMPANY");

    private final String code;

    public String code() {
        return code;
    }

    PersonRole(String code) {
        this.code = code;
    }
}
