package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum EducationType {
    HIGHER("H"),
    PROFESSIONAL_TRAINING("PT"),
    POSTGRADUATE_TRAINING("PG"),
    SECONDARY_PROFESSIONAL_EDUCATION("SPE");

    private final String code;
    public String code() {
        return code;
    }
    EducationType(String code) {
        this.code = code;
    }


    public static EducationType selectByCode(String code) {
        return Arrays.stream(EducationType.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[EducationType] Unknown reference data code! Code: " + code));
    }
}
