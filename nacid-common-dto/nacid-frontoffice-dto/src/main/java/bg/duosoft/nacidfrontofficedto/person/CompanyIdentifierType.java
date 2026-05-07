package bg.duosoft.nacidfrontofficedto.person;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 19.09.2022
 * Time: 17:12
 */
public enum CompanyIdentifierType {

    BG_IDENTIFICATION_CODE("EIK");

    private String code;

    public String getCode() {
        return code;
    }

    CompanyIdentifierType(String code) {
        this.code = code;
    }

    public static CompanyIdentifierType fromCode(String code) {
        if (code == null || code.isEmpty()) {
            return null;
        }

        return Arrays.stream(CompanyIdentifierType.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }
}
