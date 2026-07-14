package bg.duosoft.email.nacidemailproducer.events.mail.listener;

import bg.duosoft.email.nacidemailproducer.domain.core.CEmailNotification;
import bg.duosoft.email.nacidemailproducer.domain.core.CEmailParticipants;
import bg.duosoft.email.nacidemailproducer.events.mail.domain.EmailNotificationEvent;
import bg.duosoft.email.nacidemailproducer.events.mail.listener.base.MailApplicationListener;
import bg.duosoft.email.nacidemailproducer.exception.EmailTemplateNotFoundException;
import bg.duosoft.email.nacidemailproducer.utils.MailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class EmailNotificationListener extends MailApplicationListener<EmailNotificationEvent> {

    @Override
    protected String eventName() {
        return "EmailNotificationEvent";
    }

    protected CEmailNotification buildNotification(EmailNotificationEvent event) {
        if (Objects.isNull(event.getTemplate())) {
            log.error("===[MAIL PRODUCER]=== Cannot build notification, because email template is empty !");
            throw new EmailTemplateNotFoundException("===[MAIL PRODUCER]=== Cannot build notification, because email template is empty !");
        }

        Map<String, String> templateParams = MailUtils.normalizeTemplateParams(event.getTemplateParams());
        CEmailParticipants participants = CEmailParticipants.builder().to(event.getRecipients()).build();
        return mailNotificationService.buildNotification(event.getTemplate().name(), participants, templateParams);
    }

}