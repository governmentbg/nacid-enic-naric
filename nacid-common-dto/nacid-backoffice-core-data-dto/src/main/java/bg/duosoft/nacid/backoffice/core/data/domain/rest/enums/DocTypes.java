package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum DocTypes {
    CERTIFICATE(1),
    DIPLOMA_SCIENCE_DEGREE(5),
    LETTER_TO_APPLICANT(10),
    CERTIFICATE_OBVIOUS_MISTAKE_REQUEST(13),
    CERTIFICATE_OBVIOUS_MISTAKE(14),
    CERTIFICATE_DUPLICATE(16),
    CERTIFICATE_DUPLICATE_REQUEST(17),
    CERTIFICATE_REVOKED(42),
    VERIFICATION_LETTER_REVOKED(215),
    OFFICIAL_NOTE_REVOKED(216),
    DISSERTATION_WORK(43),
    REJECTION_DECISION(35),
    ABSTRACT(45),
    DESTROYED_CERTIFICATE(46),
    OTHER_DOCS(108),
    TERMINATION_ORDER(111),
    VERIFICATION_LETTER(204),
    OFFICIAL_NOTE_SECONDARY(205),
    INVALIDATION_ORDER(47)
    ;

    private final Integer code;

    public Integer code() {
        return code;
    }

    DocTypes(Integer code) {
        this.code = code;
    }

    public static DocTypes selectByCode(Integer code) {
        return Arrays.stream(DocTypes.values())
                .filter(r -> Objects.equals(code, r.code))
                .findFirst().orElse(null);
    }
}
