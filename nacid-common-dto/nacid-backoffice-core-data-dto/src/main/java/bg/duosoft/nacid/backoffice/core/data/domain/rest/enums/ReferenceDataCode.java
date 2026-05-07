package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum ReferenceDataCode {
    BIBLIOGRAPHIC_REF_SEARCH_TYPE_NACID("NTPB"),
    BIBLIOGRAPHIC_REF_SEARCH_TYPE_FOREIGN("FDBIR"),
    CERTIFICATE_STATUS_PUBLISHED("P"),
    CERTIFICATE_STATUS_DIMINISH("W"),
    CERTIFICATE_STATUS_DESTROYED("D"),
    REPORT_FIELD_TYPE_TEXT("TXT"),
    REPORT_FIELD_TYPE_HTML("HTML"),
    REPORT_FIELD_TYPE_SPRING_EXPRESSION_LANGUAGE("SPEL"),
    COMMISSION_PARTICIPATION_MEMBER("MEM");

    private final String code;

    public String code() {
        return code;
    }

    ReferenceDataCode(String code) {
        this.code = code;
    }

    public static ReferenceDataCode selectByCode(String code) {
        return Arrays.stream(ReferenceDataCode.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[ReferenceDataCode] Unknown reference data code! Code: " + code));
    }

}
