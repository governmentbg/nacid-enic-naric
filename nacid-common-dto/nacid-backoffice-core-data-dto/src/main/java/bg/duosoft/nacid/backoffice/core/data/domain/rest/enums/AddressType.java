
package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum AddressType {
    UNIVERSITY("UA"),
    TRAINING_INSTITUTION("TIA"),
    PROFESSIONAL_INSTITUTION("PIA"),
    DOCUMENT("DA"),
    COMMISSION_MEMBER("CMA"),
    COMPETENT_INSTITUTION("CIA"),
    CONTACT("CA");

    private final String code;

    public String code() {
        return code;
    }

    AddressType(String code) {
        this.code = code;
    }

    public static AddressType selectByCode(String code) {
        return Arrays.stream(AddressType.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[AddressType] Unknown reference data code! Code: " + code));
    }

}
