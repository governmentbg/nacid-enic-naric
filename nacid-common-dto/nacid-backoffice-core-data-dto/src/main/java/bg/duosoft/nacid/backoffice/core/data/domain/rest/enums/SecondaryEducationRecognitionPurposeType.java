package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum SecondaryEducationRecognitionPurposeType {
    CONTINUE_EDUCATION("AHE"),
    OBTAINING_DRIVING_LICENSE("ODL"),
    LABOR_MARKET_ACCESS("ALM"),
    OTHER("OTH");

    private final String code;

    public String code() {
        return code;
    }

    SecondaryEducationRecognitionPurposeType(String code) {
        this.code = code;
    }

    public static SecondaryEducationRecognitionPurposeType selectByCode(String code) {
        return Arrays.stream(SecondaryEducationRecognitionPurposeType.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[RecognitionPurposeType] Unknown reference data code! Code: " + code));
    }

}
