package bg.duosoft.nacid.backoffice.abdocs.domain;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Objects;

public enum DocRegistrationType {
    ByCaseRegistrationNumber(1),
    ByCorrespondentGroup(2),
    ByDocType(3),
    ExternalRegistrationNumber(4),
    ManualRegistration(5),
    Legacy(6),
    ByParentDocRegistrationNumber(7),
    ByCorrespondent(8);

    DocRegistrationType(int value) {
        this.value = value;
    }

    private final int value;

    @JsonValue
    public int value() {
        return value;
    }

    public static DocRegistrationType selectByValue(Integer value) {
        return Arrays.stream(DocRegistrationType.values())
                .filter(r -> Objects.equals(value, r.value()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[DocRegistrationType] Unknown value! Value: " + value));
    }

}