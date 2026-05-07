package bg.duosoft.nacidfrontofficedto.nomenclature;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.10.2022
 * Time: 17:46
 */
public enum EducationType {
    HIGHER_EDUCATION("H"),
    PROFESSIONAL_EDUCATION("PT"),
    AFTER_DIPLOMA_QUALIFICATION("PG"),
    SECONDARY_PROFESSIONAL_EDUCATION("SPE");

    private String code;

    public String getCode() {
        return code;
    }

    EducationType(String code) {
        this.code = code;
    }

    public static EducationType fromCode(String code) {
        if (code == null || code.isEmpty()) {
            return null;
        }

        return Arrays.stream(EducationType.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

}
