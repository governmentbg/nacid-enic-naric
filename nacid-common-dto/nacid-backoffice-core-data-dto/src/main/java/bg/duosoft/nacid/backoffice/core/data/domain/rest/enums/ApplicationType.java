
package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum ApplicationType {
    RUDI("AR"),
    LIBSERV("LIB"),
    REGPROF("RP"),
    SE_RECOGNITION("SE");

    private final String code;

    public String code() {
        return code;
    }

    ApplicationType(String code) {
        this.code = code;
    }

    public static ApplicationType selectByCode(String code) {
        return Arrays.stream(ApplicationType.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[ApplicationType] Unknown code! Code: " + code));
    }

}
