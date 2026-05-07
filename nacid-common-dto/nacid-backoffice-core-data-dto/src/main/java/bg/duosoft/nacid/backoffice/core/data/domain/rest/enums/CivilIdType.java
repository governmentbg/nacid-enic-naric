package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum CivilIdType {
    EGN("EGN"),
    LNCH("LNC"),
    EIK("EIK"),
    FOREIGN_COUNTRY_ID("DOC");

    private final String code;

    public String code() {
        return code;
    }

    CivilIdType(String code) {
        this.code = code;
    }

    public static CivilIdType selectByCode(String code) {
        return Arrays.stream(CivilIdType.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[CivilIdType] Unknown reference data code! Code: " + code));
    }

    public static CivilIdType fromName(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }

        return Arrays.stream(CivilIdType.values())
                .filter(t -> t.name().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
