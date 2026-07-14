package bg.duosoft.nacidfrontofficedto.services.inquiry;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 28.02.2023
 * Time: 11:58
 */
public enum InquiryKind {

    CITINGS("CIT"), IMPACT_FACTOR ("IMP"), IMPACT_FACTOR_CITINGS("IFCIT");

    private String code;

    public String getCode() {
        return code;
    }

    InquiryKind(String code) {
        this.code = code;
    }

    public static InquiryKind fromCode(String code) {
        if (code == null || code.isEmpty()) {
            return null;
        }

        return Arrays.stream(InquiryKind.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }
}
