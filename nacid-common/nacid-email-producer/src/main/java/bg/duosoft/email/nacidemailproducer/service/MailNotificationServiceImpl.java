package bg.duosoft.email.nacidemailproducer.service;

import bg.duosoft.email.nacidemailproducer.domain.core.CEmailNotification;
import bg.duosoft.email.nacidemailproducer.domain.core.CEmailParticipants;
import bg.duosoft.email.nacidemailproducer.domain.core.CEmailTemplate;
import bg.duosoft.email.nacidemailproducer.domain.entity.EEmailNotification;
import bg.duosoft.email.nacidemailproducer.domain.mapper.EmailNotificationMapper;
import bg.duosoft.email.nacidemailproducer.exception.EmailNotificationNotFoundException;
import bg.duosoft.email.nacidemailproducer.exception.EmailTemplateNotFoundException;
import bg.duosoft.email.nacidemailproducer.filter.EmailNotificationFilter;
import bg.duosoft.email.nacidemailproducer.repository.EmailNotificationRepository;
import bg.duosoft.email.nacidemailproducer.repository.custom.EmailNotificationRepositoryCustom;
import bg.duosoft.email.nacidemailproducer.utils.DateUtils;
import bg.duosoft.email.nacidemailproducer.utils.MailUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@Service
@Transactional(value = "pdbTransactionManager")
@RequiredArgsConstructor
public class MailNotificationServiceImpl implements MailNotificationService {

    private final MailQueueService mailQueueService;
    private final MailTemplateService mailTemplateService;
    private final EmailNotificationMapper notificationMapper;
    private final EmailNotificationRepository notificationRepository;
    private final EmailNotificationRepositoryCustom notificationRepositoryCustom;

    @Override
    public CEmailNotification buildNotification(String templateId, CEmailParticipants participants, Map<String, String> parameters) {
        CEmailTemplate template = mailTemplateService.findById(templateId);
        if (Objects.isNull(template)) {
            log.error("===[MAIL PRODUCER]=== Cannot find email template with ID = {}", templateId);
            throw new EmailTemplateNotFoundException("Cannot find email template with ID = " + templateId);
        }

        return CEmailNotification.builder()
                .subject(template.getSubject())
                .text(MailUtils.replaceTemplatePlaceholders(parameters, template.getText()))
                .recipients(participants.joinTo())
                .replyTo(participants.getReplyTo())
                .cc(participants.joinCc())
                .bcc(participants.joinBcc())
                .isHtml(template.getIsHtml())
                .createdDate(new Date())
                .skipSending(false)
                .build();
    }

    @Override
    public CEmailNotification saveNotification(CEmailNotification notification) {
        EEmailNotification entity = notificationMapper.toEntity(notification);
        if (Objects.isNull(entity))
            return null;

        return notificationMapper.toCore(notificationRepository.save(entity));
    }

    @Override
    public CEmailNotification saveNotificationAndSendToQueue(CEmailNotification notification) {
        CEmailNotification result = saveNotification(notification);
        mailQueueService.sendPortalMailToQueue(result.getId());
        return result;
    }

    @Override
    public void resendNotification(Integer id) {
        log.info("===[MAIL PRODUCER]=== Resending notification with ID = {}", id);
        EEmailNotification entity = notificationRepository.findById(id).orElse(null);
        if (Objects.isNull(entity)) {
            throw new EmailNotificationNotFoundException("Cannot find email notification with ID = " + id);
        }

        String existingComment = StringUtils.hasText(entity.getComment()) ? entity.getComment() : "";
        String newComment = String.format("%s Notification has been resent on [%s]. Previous sent date [%s]", existingComment, DateUtils.formatDateTime(new Date()), DateUtils.formatDateTime(entity.getSentDate()));

        entity.setComment(newComment);
        entity.setSkipSending(false);
        entity.setSentDate(null);

        notificationRepository.save(entity);
        mailQueueService.sendPortalMailToQueue(entity.getId());
        log.info("===[MAIL PRODUCER]=== Notification with ID = {} has been resent successfully !", id);
    }

    @Override
    public List<CEmailNotification> selectEmailNotifications(EmailNotificationFilter filter) {
        List<CEmailNotification> result = notificationRepositoryCustom.selectEmailNotifications(filter);
        return result;
    }

    @Override
    public int selectEmailNotificationsCount(EmailNotificationFilter filter) {
        return notificationRepositoryCustom.selectEmailNotificationsCount(filter);
    }

    @Override
    public void deleteEmailNotification(Integer id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public CEmailNotification findById(Integer id) {
        Optional<EEmailNotification> notificationOptional = notificationRepository.findById(id);
        return notificationOptional.map(notificationMapper::toCore).orElse(null);
    }
}
