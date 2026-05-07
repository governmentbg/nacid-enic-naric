package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

/**
 * User: ggeorgiev
 * Date: 09.06.2023
 * Time: 12:59
 */
public enum ExternalNomenclatureSystem {
    RAS("RAS"),
    ABDOCS("ABDOCS"),
    ;
    private final String code;

    public String code() {
        return code;
    }

    ExternalNomenclatureSystem(String code) {
        this.code = code;
    }

    public static ExternalNomenclatureSystem selectByCode(String code) {
        return Arrays.stream(ExternalNomenclatureSystem.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[ExternalNomenclatureSystem] Unknown code! Code: " + code));
    }
}
