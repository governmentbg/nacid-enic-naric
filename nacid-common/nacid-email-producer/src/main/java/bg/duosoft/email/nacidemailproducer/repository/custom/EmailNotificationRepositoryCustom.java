package bg.duosoft.email.nacidemailproducer.repository.custom;

import bg.duosoft.email.nacidemailproducer.domain.core.CEmailNotification;
import bg.duosoft.email.nacidemailproducer.filter.EmailNotificationFilter;

import java.util.List;

public interface EmailNotificationRepositoryCustom {

    List<CEmailNotification> selectEmailNotifications(EmailNotificationFilter filter);

    int selectEmailNotificationsCount(EmailNotificationFilter filter);

}
