package bg.duosoft.email.nacidemailproducer.events.mail.listener.base;

import bg.duosoft.email.nacidemailproducer.domain.core.CEmailNotification;
import bg.duosoft.email.nacidemailproducer.events.mail.domain.MailApplicationEvent;
import bg.duosoft.email.nacidemailproducer.service.MailNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public abstract class MailApplicationListener<E extends MailApplicationEvent> implements ApplicationListener<E> {

    @Autowired
    protected MailNotificationService mailNotificationService;

    @Override
    public void onApplicationEvent(E event) {
        this.processEvent(event);
    }

    void processEvent(E event) {
        CEmailNotification notification = buildNotification(event);
        if (Objects.isNull(notification)) {
            log.error("===[MAIL PRODUCER]=== {} failed, because of empty notification object!", eventName());
            log.error("===[MAIL PRODUCER]=== {} data: {} ", eventName(), event.toString());
        } else {
            CEmailNotification result = mailNotificationService.saveNotificationAndSendToQueue(notification);
            log.info("===[MAIL PRODUCER]=== {} has been registered successfully! Notification ID: {}", eventName(), result.getId());
        }
    }

    protected abstract String eventName();

    protected abstract CEmailNotification buildNotification(E event);

}
