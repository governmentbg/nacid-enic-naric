package bg.duosoft.nacidfrontofficedto.services.common.application;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 10.10.2022
 * Time: 17:08
 */
public enum ApplicationSubtype {
    HE_RECOGNITION("UDI"),
    SE_RECOGNITION("REC"),
    RUDI_ADDITIONAL_DOCUMENTS("ARD"),
    REGPROF_ADDITIONAL_DOCUMENTS("RPD"),
    LIBSERV_ADDITIONAL_DOCUMENTS("LIBD"),
    SECONDARY_ADDITIONAL_DOCUMENTS("SED"),
    REGPROF_DUPLICATE("RPDU"),
    RUDI_DUPLICATE("ARDU"),
    SECONDARY_DUPLICATE("SEDU"),
    DOC_DEGREES("DOC"),
    UNI_CHECKS("SAR"),
    REGULATED_PROFESSIONS("RP"),
    OFFICIAL_NOTE("ON"),
    INQUIRY("INQ"),
    BIBLIO_REFERENCE("BR"),
    DOCUMENT_SERVICE("DS"),
    SIGNAL("SIG"),
    SUGGESTION("SUG"),
    PUBLIC_ACCESS("PUB");

    private String code;

    public String getCode() {
        return code;
    }

    ApplicationSubtype(String code) {
        this.code = code;
    }

    public static ApplicationSubtype fromCode(String code) {
        if (code == null || code.isEmpty()) {
            return null;
        }

        return Arrays.stream(ApplicationSubtype.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    public boolean isAdditionalDocuments() {
        return this == RUDI_ADDITIONAL_DOCUMENTS
                || this == REGPROF_ADDITIONAL_DOCUMENTS
                || this == LIBSERV_ADDITIONAL_DOCUMENTS
                || this == SECONDARY_ADDITIONAL_DOCUMENTS;
    }

    public boolean isDuplicate() {
        return this == RUDI_DUPLICATE
                || this == REGPROF_DUPLICATE
                || this == SECONDARY_DUPLICATE;
    }
}
