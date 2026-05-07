
package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum LegalType {
    NATURAL_PERSON("NP"),
    LEGAL_ENTITY("LE");

    private final String code;

    public String code() {
        return code;
    }

    LegalType(String code) {
        this.code = code;
    }

    public static LegalType selectByCode(String code) {
        return Arrays.stream(LegalType.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[LegalType] Unknown reference data code! Code: " + code));
    }

}
