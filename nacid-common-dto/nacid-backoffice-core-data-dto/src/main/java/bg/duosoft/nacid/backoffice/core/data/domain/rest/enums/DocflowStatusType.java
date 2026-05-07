package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

public enum DocflowStatusType {
    ARCHIVED("ARC"),
    ISSUED("ISD"),
    PROCESS("POS"),
    FINISHED("FIN"),
    APPEALED("APD"),
;
    private final String code;

    public String code() {
        return code;
    }

    DocflowStatusType(String code) {
        this.code = code;
    }

}
