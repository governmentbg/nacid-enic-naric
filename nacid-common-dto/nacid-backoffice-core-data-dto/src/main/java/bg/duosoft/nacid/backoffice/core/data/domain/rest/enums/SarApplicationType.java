
package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum SarApplicationType {
    STATUTE("S"),
    AUTHENTICITY("A"),
    RECOMMENDATION("R");

    private final String code;

    public String code() {
        return code;
    }

    SarApplicationType(String code) {
        this.code = code;
    }

    public static SarApplicationType selectByCode(String code) {
        return Arrays.stream(SarApplicationType.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[SarApplicationType] Unknown code! Code: " + code));
    }

}
