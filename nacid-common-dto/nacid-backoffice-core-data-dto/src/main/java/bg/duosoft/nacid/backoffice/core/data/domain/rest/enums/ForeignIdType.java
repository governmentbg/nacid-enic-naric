package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum ForeignIdType {
    NATIONAL_ID_NUMBER("NIN"),
    SOCIAL_SEC_NUMBER("SSN"),
    PERSONAL_DOC_NUMBER("PDN"),
    NACID_GENERATED_NUMBER("OGN");

    private final String code;

    public String code() {
        return code;
    }

    ForeignIdType(String code) {
        this.code = code;
    }

    public static ForeignIdType selectByCode(String code) {
        return Arrays.stream(ForeignIdType.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[ForeignIdType] Unknown reference data code! Code: " + code));
    }

}
