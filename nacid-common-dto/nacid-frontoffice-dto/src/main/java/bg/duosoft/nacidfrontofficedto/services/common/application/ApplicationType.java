package bg.duosoft.nacidfrontofficedto.services.common.application;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 10.10.2022
 * Time: 17:06
 */
public enum ApplicationType {
    ACADEMIC_RECOGNITION("AR"),
    REGULATED_PROFESSIONS("RP"),
    SE_RECOGNITION("SE"),
    LIBRARY("LIB");

    private String code;

    public String getCode() {
        return code;
    }

    ApplicationType(String code) {
        this.code = code;
    }

    public static ApplicationType fromCode(String code) {
        if (code == null || code.isEmpty()) {
            return null;
        }

        return Arrays.stream(ApplicationType.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

}
