
package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum ApplicationSubType {
    RUDI_SAR(ApplicationType.RUDI.code(), "SAR"),
    RUDI_DOC_DEGREE_RECOGNITION(ApplicationType.RUDI.code(), "DOC"),
    RUDI_UNI_DIPLOMA_RECOGNITION(ApplicationType.RUDI.code(), "UDI"),

    LIBSERV_DOCUMENT_DELIVERY(ApplicationType.LIBSERV.code(), "DS"),
    LIBSERV_BIBLIOGRAPHIC_REFERENCE(ApplicationType.LIBSERV.code(), "BR"),
    LIBSERV_INQUIRY(ApplicationType.LIBSERV.code(), "INQ"),
    LIBSERV_OFFICIAL_NOTE(ApplicationType.LIBSERV.code(), "ON"),
    LIBSERV_SIGNAL(ApplicationType.LIBSERV.code(), "SIG"),
    LIBSERV_SUGGESTION(ApplicationType.LIBSERV.code(), "SUG"),
    LIBSERV_PUBLIC_ACCESS(ApplicationType.LIBSERV.code(), "PUB"),

    REGPROF_EDU(ApplicationType.REGPROF.code(), "ED"),
    REGPROF_INTERNSHIP(ApplicationType.REGPROF.code(), "EX"),
    REGPROF_EDU_AND_INTERNSHIP(ApplicationType.REGPROF.code(), "EDEX"),

    SE_RECOGNITION(ApplicationType.SE_RECOGNITION.code(), "REC"),
    SE_VERIFICATION_LETTER(ApplicationType.SE_RECOGNITION.code(), "SECR"),
    SE_OFFICIAL_NOTE(ApplicationType.SE_RECOGNITION.code(), "SEON"),

    ADDITIONAL_DOCUMENTS_SE(ApplicationType.SE_RECOGNITION.code(), "SED"),
    ADDITIONAL_DOCUMENTS_RUDI(ApplicationType.RUDI.code(), "ARD"),
    ADDITIONAL_DOCUMENTS_REGPROF(ApplicationType.REGPROF.code(), "RPD"),
    ADDITIONAL_DOCUMENTS_LIBSERV(ApplicationType.LIBSERV.code(), "LIBD"),

    DUPLICATE_REGPROF(ApplicationType.REGPROF.code(), "RPDU"),
    DUPLICATE_RUDI(ApplicationType.RUDI.code(), "ARDU"),
    DUPLICATE_SE(ApplicationType.SE_RECOGNITION.code(), "SEDU")
    ;
    private final String appType;
    private final String appSubType;

    public String appType() {
        return appType;
    }

    public String appSubType() {
        return appSubType;
    }

    ApplicationSubType(String appType, String appSubType) {
        this.appType = appType;
        this.appSubType = appSubType;
    }

    public static ApplicationSubType selectByTypeAndSubType(String appType, String appSubType) {
        return Arrays.stream(ApplicationSubType.values())
                .filter(r -> Objects.equals(appType, r.appType) && Objects.equals(appSubType, r.appSubType))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[ApplicationSubType] Unknown application subtype! App type: " + appType + " , App subtype: " + appSubType));
    }

}
