package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum TrainingDocumentExaminationSource {
    REGISTER("R"),
    COMMUNICATED_INSTITUTION("CI");

    private final String code;

    public String code() {
        return code;
    }

    TrainingDocumentExaminationSource(String code) {
        this.code = code;
    }

    public static TrainingDocumentExaminationSource selectByCode(String code) {
        return Arrays.stream(TrainingDocumentExaminationSource.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[TrainingDocumentExaminationSource] Unknown code! Code: " + code));
    }
}
