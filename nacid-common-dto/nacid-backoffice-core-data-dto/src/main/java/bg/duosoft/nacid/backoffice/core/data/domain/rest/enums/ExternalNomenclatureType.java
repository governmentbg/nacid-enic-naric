package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

/**
 * User: ggeorgiev
 * Date: 09.06.2023
 * Time: 13:01
 */
public enum ExternalNomenclatureType {
    RAS_INSTITUTION("RAS_INSTITUTION"),
    RAS_RUDI_APPLICATION_TYPE("RAS_RUDI_APPLICATION_TYPE"),
    RAS_COUNTRY("RAS_COUNTRY"),
    RAS_EDU_LEVEL("RAS_EDU_LEVEL"),
    RAS_PROF_GROUP("RAS_PROF_GROUP"),
    RAS_LANGUAGE("RAS_LANGUAGE"),
    ABDOCS_SETTLEMENT("ABDOCS_SETTLEMENT"),
    ;
    private final String code;

    public String code() {
        return code;
    }

    ExternalNomenclatureType(String code) {
        this.code = code;
    }

    public static ExternalNomenclatureType selectByCode(String code) {
        return Arrays.stream(ExternalNomenclatureType.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[ExternalNomenclatureType] Unknown code! Code: " + code));
    }
}
