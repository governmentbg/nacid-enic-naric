package bg.duosoft.nacidfrontofficedto.services.common.application;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 23.12.2022
 * Time: 16:31
 */
public enum FoApplicationStatus {

    DRAFT("DRFT"),
    FINALIZED("FIN"),
    SUBMITTED("SUB"),
    SUBMITTED_WITH_SIGNATURE("SIG"),
    ACCEPTED("ACC"),
    ACCEPTANCE_DENIED("ADEN"),
    PUBLISHED("PUB"),
    FOR_CORRECTION("CORR");


    private String code;

    public String getCode() {
        return code;
    }

    FoApplicationStatus(String code) {
        this.code = code;
    }

    public static FoApplicationStatus fromCode(String code) {
        if (code == null || code.isEmpty()) {
            return null;
        }

        return Arrays
                .stream(FoApplicationStatus.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }
}
