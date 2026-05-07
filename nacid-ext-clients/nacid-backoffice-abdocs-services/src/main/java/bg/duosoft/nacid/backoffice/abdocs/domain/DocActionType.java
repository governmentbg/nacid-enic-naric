package bg.duosoft.nacid.backoffice.abdocs.domain;

public enum DocActionType {
    Task(12),
    Targeting(13);

    DocActionType(int value) {
        this.value = value;
    }

    private final int value;

    public int value() {
        return this.value;
    }

}