package bg.duosoft.nacid.clients.signature.model;

public enum FileStatusEnum {
    SIGNING_NOT_ALLOWED("SIGNING_NOT_ALLOWED"),
    NOT_EXIST("NOT_EXIST"),
    NACID_SIGNED("NACID_SIGNED"),
    CUSTOMER_SIGNED("CUSTOMER_SIGNED");

    FileStatusEnum(String code) {
        this.code = code;
    }

    private String code;

    public String value() {
        return name();
    }

    public String code() {
        return code;
    }
}
