package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.Objects;

public enum OfficialNoteKind {
    DISSERTATION("DIS"),
    POSITION("POS");
//    PROJECT("PRJ"),
//    PAPER("PAP");

    private final String code;

    public String code() {
        return code;
    }

    OfficialNoteKind(String code) {
        this.code = code;
    }

    public static OfficialNoteKind selectByCode(String code) {
        return Arrays.stream(OfficialNoteKind.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("[OfficialNoteKind] Unknown reference data code! Code: " + code));
    }

}
