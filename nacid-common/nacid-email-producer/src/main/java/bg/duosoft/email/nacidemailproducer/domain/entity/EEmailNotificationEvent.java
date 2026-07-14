package bg.duosoft.email.nacidemailproducer.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "email_notification_event", schema = "emails")
@Cacheable(value = false)
public class EEmailNotificationEvent implements Serializable {

    @Id
    @Column(name = "code")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "notify_emails")
    private String notifyEmails;

    @Column(name = "notify_groups")
    private String notifyGroups;

    @Column(name = "notify_users")
    private String notifyUsers;

}
