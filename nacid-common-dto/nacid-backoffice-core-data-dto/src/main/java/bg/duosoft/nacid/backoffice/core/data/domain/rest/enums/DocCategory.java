package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum DocCategory {
    COMMISSION_MEMBER("CMA"),
    UNI_EXAM("UEA"),
    APP_ATTACHMENTS("AA"),
    DIPLOMA_EXAM("DEA"),
    AUTH_DOC_EXAM("DAA"),
    PROF_EXPERIENCE_EXAM("EDV");
    private final String code;

    public String code() {
        return code;
    }

    DocCategory(String code) {
        this.code = code;
    }

    public static DocCategory selectByCode(String code) {
        return Arrays.stream(DocCategory.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[DocCategory] Unknown reference data code! Code: " + code));
    }

}
