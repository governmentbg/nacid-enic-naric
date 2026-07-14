package bg.duosoft.email.nacidemailproducer.events.mail.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SimpleMailEvent extends MailApplicationEvent {
    private String message;
    private String recipients;
    private String subject;

    private SimpleMailEvent(Object source, String recipients, String message, String subject) {
        super(source);
        this.message = message;
        this.recipients = recipients;
        this.subject = subject;
    }

    public static SimpleMailEvent newEvent(Object source, String recipients, String message, String subject) {
        return new SimpleMailEvent(source, recipients, message, subject);
    }
}
