package bg.duosoft.email.nacidemailproducer.events.mail.domain;

import org.springframework.context.ApplicationEvent;

public class MailApplicationEvent extends ApplicationEvent {
    public MailApplicationEvent(Object source) {
        super(source);
    }

}
