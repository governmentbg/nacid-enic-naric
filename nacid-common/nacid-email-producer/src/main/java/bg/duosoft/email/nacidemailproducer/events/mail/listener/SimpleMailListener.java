package bg.duosoft.email.nacidemailproducer.events.mail.listener;

import bg.duosoft.email.nacidemailproducer.events.mail.domain.SimpleMailEvent;
import bg.duosoft.email.nacidemailproducer.service.MailQueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleMailListener implements ApplicationListener<SimpleMailEvent> {

    @Autowired
    private MailQueueService mailQueueService;

    @Override
    public void onApplicationEvent(SimpleMailEvent event) {
        mailQueueService.sendSimpleMailToQueue(event.getRecipients(), event.getMessage(), event.getSubject());
        log.info("===[MAIL PRODUCER]=== SimpleMailEvent has been registered successfully! Recipients: {}, Subject: {}", event.getRecipients(), event.getSubject());
    }
}