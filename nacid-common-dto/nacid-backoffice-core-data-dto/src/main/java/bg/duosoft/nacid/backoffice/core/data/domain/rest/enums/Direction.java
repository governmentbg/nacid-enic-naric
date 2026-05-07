package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum Direction {
    Output("O"),
    Input("I");
    private final String code;
    public String code() {
        return code;
    }
    Direction(String code) {
        this.code = code;
    }


    public static Direction selectByCode(String code) {
        return Arrays.stream(Direction.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[Direction] Unknown reference data code! Code: " + code));
    }

}
