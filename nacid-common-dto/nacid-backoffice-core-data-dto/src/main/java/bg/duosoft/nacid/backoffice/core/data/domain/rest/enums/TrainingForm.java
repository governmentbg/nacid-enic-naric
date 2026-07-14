
package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum TrainingForm {
    DISTANCE_LEARNING("DL"),
    FULL_TIME_TRAINING("FTT"),
    PART_TIME_TRAINING("PTT"),
    NIGHT_LEARNING("NL"),
    OTHER("OTH");

    private final String code;

    public String code() {
        return code;
    }

    TrainingForm(String code) {
        this.code = code;
    }

    public static TrainingForm selectByCode(String code) {
        return Arrays.stream(TrainingForm.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[TrainingForm] Unknown reference data code! Code: " + code));
    }

}
