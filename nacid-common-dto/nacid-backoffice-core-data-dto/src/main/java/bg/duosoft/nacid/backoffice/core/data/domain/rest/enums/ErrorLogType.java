
package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum ErrorLogType {
    UPDATE_FO_APP_DATA_ON_ACCEPT("UPDATE_FO_APP_DATA_ON_ACCEPT"),
    DMS_ACCEPT_ABDOCS_REGISTRATION("DMS_ACCEPT_ABDOCS_REGISTRATION"),
    DMS_ACCEPT_FO_UPDATE("DMS_ACCEPT_FO_UPDATE"),
    UPDATE_PAYMENTS_DATA_ON_ACCEPT("UPDATE_PAYMENTS_DATA_ON_ACCEPT"),
    DMS_ACCEPT_RECEIPT_INSERT("DMS_ACCEPT_RECEIPT_INSERT");

    private final String code;

    public String code() {
        return code;
    }

    ErrorLogType(String code) {
        this.code = code;
    }

    public static ErrorLogType selectByCode(String code) {
        return Arrays.stream(ErrorLogType.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[ErrorLogType] Unknown code! Code: " + code));
    }

}
