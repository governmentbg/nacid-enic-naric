package bg.duosoft.nacidfrontofficedto.services.officialnotes;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.02.2023
 * Time: 15:08
 */
public enum OfficialNoteKind {

    PROJECT_NOTE("PRJ"), PAPER_NOTE ("PAP"), DISSERTATION_NOTE("DIS"), POSITION_NOTE("POS");

    private String code;

    public String getCode() {
        return code;
    }

    OfficialNoteKind(String code) {
        this.code = code;
    }

    public static OfficialNoteKind fromCode(String code) {
        if (code == null || code.isEmpty()) {
            return null;
        }

        return Arrays.stream(OfficialNoteKind.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }
}
