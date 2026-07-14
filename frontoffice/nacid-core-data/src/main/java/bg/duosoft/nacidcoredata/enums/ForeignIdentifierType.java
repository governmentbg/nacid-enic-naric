package bg.duosoft.nacidcoredata.enums;

import lombok.Getter;

@Getter
public enum ForeignIdentifierType {
    NATIONAL_IDENTIFICATION_NUMBER("NIN"),
    SOCIAL_SECURITY_NUMBER("SSN"),
    PERSONAL_DOCUMENT_NUMBER("PDN"),
    OFFICIALLY_GENERATED_BY_NACID("OGN");

    ForeignIdentifierType(String code) {
        this.code = code;
    }

    private final String code;
}
