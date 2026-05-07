
package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum LegalNatureType {
    UNIVERSITY("U"),
    COMPANY("C");

    private final String code;

    public String code() {
        return code;
    }

    LegalNatureType(String code) {
        this.code = code;
    }

    public static LegalNatureType selectByCode(String code) {
        return Arrays.stream(LegalNatureType.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[LegalNatureType] Unknown reference data code! Code: " + code));
    }

}
