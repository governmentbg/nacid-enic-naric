package bg.duosoft.email.nacidemailproducer.domain.core;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class CEmailNotificationEvent implements Serializable {
    private String id;
    private String name;
    private String nameEn;
    private Set<String> notifyEmails;
    private Set<String> notifyGroups;
    private Set<String> notifyUsers;
}
