package bg.duosoft.email.nacidemailproducer.events.mail.domain;

import bg.duosoft.email.nacidemailproducer.enums.EmailNotificationEventType;
import bg.duosoft.email.nacidemailproducer.enums.MailTemplate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class EmailNotificationEvent extends MailApplicationEvent {
    private Map<String, String> templateParams;
    private List<String> recipients;
    private MailTemplate template;
    private EmailNotificationEventType type;

    private EmailNotificationEvent(Object source, Map<String, String> params, List<String> recipients, MailTemplate template, EmailNotificationEventType type) {
        super(source);
        this.templateParams = params;
        this.recipients = recipients;
        this.template = template;
        this.type = type;
    }

    public static EmailNotificationEvent newEvent(Object source, Map<String, String> params, List<String> recipients, MailTemplate template, EmailNotificationEventType type) {
        return new EmailNotificationEvent(source, params, recipients, template, type);
    }
}