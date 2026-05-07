package bg.duosoft.nacid.backoffice.core.data.domain.rest.enums;

public enum ApplicationStatusType {
    LEGITIMATE_BY_HEADQUARTERS("LBH"),
    LEGITIMATE_PROGRAM("LP"),
    LEGITIMATE_BY_TRAINING_LOCATION("LBTL"),
    AUTHENTIC("AUTH"),
    ACKNOWLEDGED("ACK"),
    SUSPEND_SUBMITTED_DOCUMENTS("PSD"),
    SUBMITTED("FILE"),
    IN_PROCESSING("PRC"),
    FOR_APPROVAL("PACK"),
    REJECTED("DEN"),
    TERMINATED("TED"),
    FOR_TERMINATION("PTED"),
    FOR_REJECTION("PDEN"),
    FOR_INVALIDATION("PWEAK"),
    INVALIDATED("WEAK");
    private final String code;

    public String code() {
        return code;
    }

    ApplicationStatusType(String code) {
        this.code = code;
    }

}
