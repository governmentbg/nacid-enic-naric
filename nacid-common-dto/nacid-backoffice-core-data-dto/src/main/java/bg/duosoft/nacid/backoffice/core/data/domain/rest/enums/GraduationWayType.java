package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum GraduationWayType {
    THESIS("T"),
    EXAM("SE"),
    THESIS_AND_EXAM("TSE"),
    DISSERTATION("DIS"),
    OTHER("OTH");

    private final String code;

    public String code() {
        return code;
    }

    GraduationWayType(String code) {
        this.code = code;
    }

    public static GraduationWayType selectByCode(String code) {
        return Arrays.stream(GraduationWayType.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[GraduationWayType] Unknown reference data code! Code: " + code));
    }

}
