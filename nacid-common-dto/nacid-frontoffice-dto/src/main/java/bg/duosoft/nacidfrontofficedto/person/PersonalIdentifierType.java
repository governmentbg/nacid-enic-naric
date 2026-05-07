package bg.duosoft.nacidfrontofficedto.person;

import java.util.Arrays;

public enum PersonalIdentifierType {
    NATIONAL_ID("EGN"),
    NATIONAL_FOREIGNER_ID("LNC"),
    DOCUMENT_ID("DOC");

    private String code;

    public String getCode() {
        return code;
    }

    PersonalIdentifierType(String code) {
        this.code = code;
    }

    public static PersonalIdentifierType fromCode(String code) {
        if (code == null || code.isEmpty()) {
            return null;
        }

        return Arrays.stream(PersonalIdentifierType.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }
    public static PersonalIdentifierType fromName(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }

        return Arrays.stream(PersonalIdentifierType.values())
                .filter(t -> t.name().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
