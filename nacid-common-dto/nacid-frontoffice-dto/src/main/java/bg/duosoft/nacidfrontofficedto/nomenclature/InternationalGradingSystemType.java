package bg.duosoft.nacidfrontofficedto.nomenclature;

import java.util.Arrays;
import java.util.Objects;

public enum InternationalGradingSystemType {
    INTERNATIONAL_BACCALAUREATE("IB"),
    EUROPEAN_BACCALAUREATE("EB")
    ;

    private final String code;

    public String code() {
        return code;
    }

    InternationalGradingSystemType(String code) {
        this.code = code;
    }

    public static InternationalGradingSystemType selectByCode(String code) {
        return Arrays.stream(InternationalGradingSystemType.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst().orElse(null);
    }
}
