package bg.duosoft.email.nacidemailproducer.service;

import bg.duosoft.email.nacidemailproducer.domain.core.CEmailNotificationEvent;

public interface MailNotificationEventService {

    CEmailNotificationEvent findById(String id);

}
