package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum RecognitionPurposeType {
    CONTINUE_EDUCATION("S"),
    WORK("W"),
    PROJECT_WORK("PW"),
    OTHER("OTH");

    private final String code;

    public String code() {
        return code;
    }

    RecognitionPurposeType(String code) {
        this.code = code;
    }

    public static RecognitionPurposeType selectByCode(String code) {
        return Arrays.stream(RecognitionPurposeType.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[RecognitionPurposeType] Unknown reference data code! Code: " + code));
    }

}
