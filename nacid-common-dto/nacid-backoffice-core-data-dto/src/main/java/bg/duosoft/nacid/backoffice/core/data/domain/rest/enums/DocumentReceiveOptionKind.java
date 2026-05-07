package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 01.03.2023
 * Time: 13:08
 */
public enum DocumentReceiveOptionKind {

    ORIGINAL_DOCUMENTS_RECEIVE_OPTION("ORIG");

    private String code;

    public String getCode() {
        return code;
    }

    DocumentReceiveOptionKind(String code) {
        this.code = code;
    }

    public static DocumentReceiveOptionKind fromCode(String code) {
        if (code == null || code.isEmpty()) {
            return null;
        }

        return Arrays.stream(DocumentReceiveOptionKind.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }
}
