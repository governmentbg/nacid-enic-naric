package bg.duosoft.email.nacidemailproducer.domain.mapper;

import bg.duosoft.email.nacidemailproducer.domain.core.CEmailNotificationEvent;
import bg.duosoft.email.nacidemailproducer.domain.entity.EEmailNotificationEvent;
import org.mapstruct.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;


@Mapper(componentModel = "spring")
public abstract class EmailNotificationEventMapper extends BaseObjectMapper<EEmailNotificationEvent, CEmailNotificationEvent> {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "nameEn", source = "nameEn")
    @BeanMapping(ignoreByDefault = true)
    public abstract CEmailNotificationEvent toCore(EEmailNotificationEvent e);

    @InheritInverseConfiguration
    public abstract EEmailNotificationEvent toEntity(CEmailNotificationEvent dto);


    @AfterMapping
    protected void afterToCore(EEmailNotificationEvent source, @MappingTarget CEmailNotificationEvent target) {
        String notifyEmails = source.getNotifyEmails();
        if (StringUtils.hasText(notifyEmails)) {
            target.setNotifyEmails(new LinkedHashSet<>());
            String[] emailsArray = notifyEmails.split(",");
            for (String email : emailsArray) {
                if (StringUtils.hasText(email)) {
                    target.getNotifyEmails().add(email.trim());
                }
            }
        }


        String notifyGroups = source.getNotifyGroups();
        if (StringUtils.hasText(notifyGroups)) {
            target.setNotifyGroups(new LinkedHashSet<>());
            String[] groupsArray = notifyGroups.split(",");
            for (String group : groupsArray) {
                if (StringUtils.hasText(group)) {
                    target.getNotifyGroups().add(group.trim());
                }
            }
        }

        String notifyUsers = source.getNotifyUsers();
        if (StringUtils.hasText(notifyUsers)) {
            target.setNotifyUsers(new LinkedHashSet<>());
            String[] usersArray = notifyUsers.split(",");
            for (String user : usersArray) {
                if (StringUtils.hasText(user)) {
                    target.getNotifyUsers().add(user.trim());
                }
            }
        }

    }


    @AfterMapping
    protected void afterToEntity(CEmailNotificationEvent source, @MappingTarget EEmailNotificationEvent target) {
        Set<String> notifyEmails = source.getNotifyEmails();
        if (!CollectionUtils.isEmpty(notifyEmails)) {
            target.setNotifyEmails(String.join(",", notifyEmails));
        }

        Set<String> notifyGroups = source.getNotifyGroups();
        if (!CollectionUtils.isEmpty(notifyGroups)) {
            target.setNotifyGroups(String.join(",", notifyGroups));
        }

        Set<String> notifyUsers = source.getNotifyUsers();
        if (!CollectionUtils.isEmpty(notifyUsers)) {
            target.setNotifyUsers(String.join(",", notifyUsers));
        }

    }

}
