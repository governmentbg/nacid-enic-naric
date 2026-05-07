package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum AttachmentVisibility {
    INTERNAL("INT"),
    PUBLIC("PUB");
    private final String code;

    public String code() {
        return code;
    }

    AttachmentVisibility(String code) {
        this.code = code;
    }

    public static AttachmentVisibility selectByCode(String code) {
        return Arrays.stream(AttachmentVisibility.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[AttachmentVisibility] Unknown reference data code! Code: " + code));
    }

}
