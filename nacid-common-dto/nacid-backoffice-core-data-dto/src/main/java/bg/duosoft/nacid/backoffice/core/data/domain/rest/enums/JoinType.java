package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

public enum JoinType {
    ANY("ANY"),
    ALL("ALL"),
    ONLY_ALL("ONLY_ALL");

    private final String code;

    public String code() {
        return code;
    }

    JoinType(String code) {
        this.code = code;
    }
}
