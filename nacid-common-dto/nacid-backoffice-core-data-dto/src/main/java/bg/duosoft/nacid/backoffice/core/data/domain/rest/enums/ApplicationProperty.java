package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

/**
 * User: ggeorgiev
 * Date: 02.11.2022
 * Time: 12:18
 */
public enum ApplicationProperty {
    REPORTS_BASE_DIR("ReportsBaseDir"),
    RUDI_QR_CODE_URL("rudiQrCodeUrl"),
    NORQ_QR_CODE_URL("norqQrCodeUrl"),
    SE_QR_CODE_URL("seQrCodeUrl"),
    ERROR_LOG_MSG_UPDATE_FO_APP_DATA_ON_ACCEPT("ERROR_LOG_MSG_UPDATE_FO_APP_DATA_ON_ACCEPT"),
    ERROR_LOG_MSG_UPDATE_PAYMENTS_DATA_ON_ACCEPT("ERROR_LOG_MSG_UPDATE_PAYMENTS_DATA_ON_ACCEPT"),
    ERROR_LOG_MSG_DMS_ACCEPT_ABDOCS_REGISTRATION("ERROR_LOG_MSG_DMS_ACCEPT_ABDOCS_REGISTRATION"),
    ERROR_LOG_MSG_DMS_ACCEPT_FO_UPDATE("ERROR_LOG_MSG_DMS_ACCEPT_FO_UPDATE"),
    ERROR_LOG_MSG_DMS_ACCEPT_RECEIPT_INSERT("ERROR_LOG_MSG_DMS_ACCEPT_RECEIPT_INSERT"),
    RUDI_CERTIFICATE_WIDTH("RUDI_CERTIFICATE_WIDTH"),
    RUDI_CERTIFICATE_HEIGHT("RUDI_CERTIFICATE_HEIGHT"),
    REGPROF_CERTIFICATE_WIDTH("REGPROF_CERTIFICATE_WIDTH"),
    REGPROF_CERTIFICATE_HEIGHT("REGPROF_CERTIFICATE_HEIGHT"),
    SE_CERTIFICATE_HEIGHT("SE_CERTIFICATE_HEIGHT"),
    SE_CERTIFICATE_WIDTH("SE_CERTIFICATE_WIDTH"),

    ;
    private final String code;

    ApplicationProperty(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

    public static ApplicationProperty selectByCode(String code) {
        return Arrays.stream(ApplicationProperty.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[ApplicationProperty] Unknown application property code! Code: " + code));
    }
}
