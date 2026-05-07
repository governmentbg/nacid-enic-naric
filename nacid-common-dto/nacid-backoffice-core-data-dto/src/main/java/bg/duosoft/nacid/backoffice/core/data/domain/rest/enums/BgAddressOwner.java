
package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum BgAddressOwner {
    APPLICANT("A"),
    REPRESENTATIVE("R");

    private final String code;

    public String code() {
        return code;
    }

    BgAddressOwner(String code) {
        this.code = code;
    }

    public static BgAddressOwner selectByCode(String code) {
        return Arrays.stream(BgAddressOwner.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[BgAddressOwner] Unknown code! Code: " + code));
    }

}
