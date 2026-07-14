package bg.duosoft.nacid.backoffice.abdocs.domain;

public enum DocSourceType {
    InternetPortal(1),
    Counter(2),
    Email(3),
    Mail(4);

    DocSourceType(int value) {
        this.value = value;
    }

    private final int value;

    public int value() {
        return this.value;
    }

}