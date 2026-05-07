package bg.duosoft.nacidfrontofficedto.person;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 19.08.2022
 * Time: 14:05
 */
public enum ApplicantType {
    NATURAL_PERSON("NP", null),
    COMPANY("LE", "C"),
    UNIVERSITY("LE", "U");

    private String legalTypeCode;
    private String legalNatureTypeCode;

    public String getLegalTypeCode() {
        return legalTypeCode;
    }

    public String getLegalNatureTypeCode() {
        return legalNatureTypeCode;
    }

    ApplicantType(String legalTypeCode, String legalNatureTypeCode) {
        this.legalTypeCode = legalTypeCode;
        this.legalNatureTypeCode = legalNatureTypeCode;
    }

    public static ApplicantType fromLegalTypeAndNatureCodes(String legalTypeCode, String legalNatureTypeCode) {
        if (legalTypeCode == null || legalTypeCode.isEmpty()) {
            return null;
        }
        if(!legalTypeCode.equals(NATURAL_PERSON.legalTypeCode) && (legalNatureTypeCode == null || legalNatureTypeCode.isEmpty())){
            return null;
        }

        return Arrays.stream(ApplicantType.values())
                .filter(
                        c -> c.getLegalTypeCode().equals(legalTypeCode) &&
                        (
                                (legalNatureTypeCode == null && legalTypeCode.equals(NATURAL_PERSON.legalTypeCode)) ||
                                        c.getLegalNatureTypeCode().equals(legalNatureTypeCode)
                        )
                ).findFirst()
                .orElse(null);
    }
}
