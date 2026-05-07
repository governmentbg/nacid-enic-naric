package bg.duosoft.email.nacidemailproducer.service;

import bg.duosoft.email.nacidemailproducer.domain.core.CEmailNotification;
import bg.duosoft.email.nacidemailproducer.domain.core.CEmailParticipants;
import bg.duosoft.email.nacidemailproducer.filter.EmailNotificationFilter;

import java.util.List;
import java.util.Map;

public interface MailNotificationService {

    CEmailNotification buildNotification(String templateId, CEmailParticipants participants, Map<String, String> parameters);

    CEmailNotification saveNotification(CEmailNotification notification);

    CEmailNotification saveNotificationAndSendToQueue(CEmailNotification notification);

    void resendNotification(Integer id);

    List<CEmailNotification> selectEmailNotifications(EmailNotificationFilter filter);

    int selectEmailNotificationsCount(EmailNotificationFilter filter);

    void deleteEmailNotification(Integer id);

    CEmailNotification findById(Integer id);

}
