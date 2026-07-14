package bg.duosoft.email.nacidemailproducer.service.event;

import bg.duosoft.email.nacidemailproducer.enums.EmailNotificationEventType;

import java.util.List;
import java.util.Map;

public interface MailEventService {

    void triggerEvent(EmailNotificationEventType event, Map<String, String> templateParams, List<String> recipientEmails);

}
