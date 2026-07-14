
package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum PersonalDocumentType {
    REFUGEE_CARD("REF"),
    RESIDENCE_CERTIFICATE("RCT"),
    FOREIGNER_HUM_STATUTE_TRAVEL_CERTIFICATE("FHSTC"),
    IDENTITY_CARD("ID"),
    DIPLOMATIC_REPR_EMPLOYEE_CARD("DC"),
    PASSPORT("PAT"),
    FOREIGNER_HUM_STATUTE_CARD("FHSC"),
    ELECTRONIC_IDENTITY("EI");

    private final String code;

    public String code() {
        return code;
    }

    PersonalDocumentType(String code) {
        this.code = code;
    }

    public static PersonalDocumentType selectByCode(String code) {
        return Arrays.stream(PersonalDocumentType.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[PersonalDocumentType] Unknown reference data code! Code: " + code));
    }

}
