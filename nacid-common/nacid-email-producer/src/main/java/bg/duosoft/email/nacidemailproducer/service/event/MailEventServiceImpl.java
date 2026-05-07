package bg.duosoft.email.nacidemailproducer.service.event;

import bg.duosoft.email.nacidemailproducer.domain.core.CEmailNotificationEvent;
import bg.duosoft.email.nacidemailproducer.enums.EmailNotificationEventType;
import bg.duosoft.email.nacidemailproducer.enums.MailTemplate;
import bg.duosoft.email.nacidemailproducer.events.mail.domain.EmailNotificationEvent;
import bg.duosoft.email.nacidemailproducer.events.mail.publisher.MailEventPublisher;
import bg.duosoft.email.nacidemailproducer.exception.EmailTemplateNotFoundException;
import bg.duosoft.email.nacidemailproducer.service.MailNotificationEventService;
import bg.duosoft.nacidfrontofficedto.user.NacidUserDetailsDTO;
import bg.duosoft.nacidkeycloakservices.service.KeycloakUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailEventServiceImpl implements MailEventService {

    private final MailEventPublisher mailEventPublisher;
    private final MailNotificationEventService mailNotificationEventService;
    private final KeycloakUserService keycloakUserService;

    @Override
    public void triggerEvent(EmailNotificationEventType type, Map<String, String> params, List<String> recipientEmails) {
        checkTemplateDefinition(type);

        List<String> recipients = selectRecipients(type, recipientEmails);
        if (!CollectionUtils.isEmpty(recipients)) {
            for (String recipient : recipients) {
                mailEventPublisher.publish(EmailNotificationEvent.newEvent(this, params, Collections.singletonList(recipient), type.getTemplate(), type));
            }
        }

    }

    private List<String> selectRecipients(EmailNotificationEventType type, List<String> recipientEmails) {
        Set<String> allRecipients = new LinkedHashSet<>();
        if (!CollectionUtils.isEmpty(recipientEmails)) {
            allRecipients.addAll(recipientEmails);
        }

        Set<String> preConfiguredRecipients = selectRecipientsFromEventConfig(type);
        if (!CollectionUtils.isEmpty(preConfiguredRecipients)) {
            allRecipients.addAll(preConfiguredRecipients);
        }

        if (CollectionUtils.isEmpty(allRecipients)) {
            log.error("===[MAIL PRODUCER]=== Cannot publish email notification event, because recipient list is empty ! Event type: {}", type.getCode());
            return null;
        }

        return allRecipients.stream().toList();
    }

    private Set<String> selectRecipientsFromEventConfig(EmailNotificationEventType type) {
        Set<String> recipients = new LinkedHashSet<>();

        CEmailNotificationEvent eventConfig = mailNotificationEventService.findById(type.getCode());
        if (Objects.nonNull(eventConfig)) {
            Set<String> notifyEmails = eventConfig.getNotifyEmails();
            if (!CollectionUtils.isEmpty(notifyEmails)) {
                recipients.addAll(notifyEmails);
            }

            Set<String> notifyGroups = eventConfig.getNotifyGroups();
            if (!CollectionUtils.isEmpty(notifyGroups)) {
                for (String kcGroup : notifyGroups) {
                    List<String> userEmails = keycloakUserService.selectEmailsOfGroupMembers(Collections.singletonList(kcGroup));
                    if (!CollectionUtils.isEmpty(userEmails)) {
                        recipients.addAll(userEmails);
                    }
                }
            }

            Set<String> notifyUsers = eventConfig.getNotifyUsers();
            if (!CollectionUtils.isEmpty(notifyUsers)) {
                for (String user : notifyUsers) {
                    NacidUserDetailsDTO kcUser = keycloakUserService.getUserByUsername(user);
                    if (Objects.nonNull(kcUser)) {
                        String email = kcUser.getEmail();
                        if (StringUtils.hasText(email)) {
                            recipients.add(email);
                        }
                    }
                }
            }

        }

        return recipients;
    }

    private static void checkTemplateDefinition(EmailNotificationEventType type) {
        MailTemplate template = type.getTemplate();
        if (Objects.isNull(template)) {
            log.error("===[MAIL PRODUCER]=== Email template is not defined for notification event {}", type.getCode());
            throw new EmailTemplateNotFoundException("===[MAIL PRODUCER]=== Email template is not defined for notification event " + type.getCode());
        }
    }
}
