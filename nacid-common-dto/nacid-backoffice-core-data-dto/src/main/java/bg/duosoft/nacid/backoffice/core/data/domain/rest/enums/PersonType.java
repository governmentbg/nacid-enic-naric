
package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum PersonType {
    NATURAL_PERSON("NATURAL_PERSON"),
    COMPANY("COMPANY"),
    UNIVERSITY("UNIVERSITY");

    private final String code;

    public String code() {
        return code;
    }

    PersonType(String code) {
        this.code = code;
    }

    public static PersonType selectByCode(String code) {
        return Arrays.stream(PersonType.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[PersonType] Unknown code! Code: " + code));
    }

}
