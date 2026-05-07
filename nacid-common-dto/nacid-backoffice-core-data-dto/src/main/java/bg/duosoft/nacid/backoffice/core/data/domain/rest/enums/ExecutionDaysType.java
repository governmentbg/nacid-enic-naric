package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum ExecutionDaysType {
    WORKING_DAY("WD"),
    CALENDAR_DAY("CD");
    private final String code;

    public String code() {
        return code;
    }

    ExecutionDaysType(String code) {
        this.code = code;
    }

    public static ExecutionDaysType selectByCode(String code) {
        return Arrays.stream(ExecutionDaysType.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[ExecutionDaysType] Unknown reference data code! Code: " + code));
    }

}
