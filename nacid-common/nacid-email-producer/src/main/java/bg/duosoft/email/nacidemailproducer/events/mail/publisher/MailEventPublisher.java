package bg.duosoft.email.nacidemailproducer.events.mail.publisher;

import bg.duosoft.email.nacidemailproducer.events.mail.domain.MailApplicationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publish(MailApplicationEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
