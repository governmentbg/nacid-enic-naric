package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum DocumentReceiveMethod {
    ELECTRONIC("E"),
    NACID("DEC"),
    INTERNATIONAL_DELIVERY("ID"),
    SYSTEM_FOR_SECURE_ELECTRONIC_DELIVERY("SSE"),
    DELIVERY("D");
    private final String code;

    public String code() {
        return code;
    }

    DocumentReceiveMethod(String code) {
        this.code = code;
    }

    public static DocumentReceiveMethod selectByCode(String code) {
        return Arrays.stream(DocumentReceiveMethod.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[DocumentReceiveMethod] Unknown code! Code: " + code));
    }

}
