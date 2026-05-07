package bg.duosoft.nacidfrontofficedto.services.biblioreference;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 01.03.2023
 * Time: 13:08
 */
public enum BibliographicReferenceResultKind {

    DESCRIPTIONS("DES"), DESCRIPTIONS_ABSTRACTS ("DESAB");

    private String code;

    public String getCode() {
        return code;
    }

    BibliographicReferenceResultKind(String code) {
        this.code = code;
    }

    public static BibliographicReferenceResultKind fromCode(String code) {
        if (code == null || code.isEmpty()) {
            return null;
        }

        return Arrays.stream(BibliographicReferenceResultKind.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }
}
