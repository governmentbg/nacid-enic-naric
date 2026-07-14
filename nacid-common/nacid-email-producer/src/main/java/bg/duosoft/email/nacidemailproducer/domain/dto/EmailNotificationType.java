
package bg.duosoft.email.nacidemailproducer.domain.dto;

import java.util.Arrays;
import java.util.Objects;

public enum EmailNotificationType {
    PORTAL_MAIL("PORTAL_MAIL"),
    SIMPLE_MAIL("SIMPLE_MAIL");

    private final String code;

    public String code() {
        return code;
    }

    EmailNotificationType(String code) {
        this.code = code;
    }

    public static EmailNotificationType selectByCode(String code) {
        return Arrays.stream(EmailNotificationType.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[EmailNotificationType] Unknown reference data code! Code: " + code));
    }

}
