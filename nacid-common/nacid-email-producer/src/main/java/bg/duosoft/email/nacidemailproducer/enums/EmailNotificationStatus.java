package bg.duosoft.email.nacidemailproducer.enums;

import lombok.Getter;

@Getter
public enum EmailNotificationStatus {
    SENT("Sent", "Изпратен"),
    NOT_SENT("Not sent", "Неизпратен"),
    IN_SENDING_PROCESS("In sending process", "В процес на изпращане");

    private final String label;
    private final String labelEn;

    EmailNotificationStatus(String labelEn, String label) {
        this.label = label;
        this.labelEn = labelEn;
    }
}
