package bg.duosoft.email.nacidemailproducer.service;

import bg.duosoft.email.nacidemailproducer.domain.dto.EmailNotificationDTO;
import bg.duosoft.email.nacidemailproducer.domain.dto.EmailNotificationType;
import bg.duosoft.email.nacidemailproducer.exception.EmailQueueException;
import bg.duosoft.email.nacidemailproducer.property.EmailProducerPropertyAccess;
import bg.duosoft.email.nacidemailproducer.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailQueueServiceImpl implements MailQueueService {

    private final RabbitTemplate rabbitTemplate;
    private final EmailProducerPropertyAccess propertyAccess;

    @Override
    public void sendMailToQueue(String message) {
        try {
            rabbitTemplate.convertAndSend(propertyAccess.getMqMailExchangeName(), propertyAccess.getMqMailRouteKey(), message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new EmailQueueException(e.getMessage(), e);
        }
    }

    @Override
    public void sendPortalMailToQueue(Integer emailNotificationId) {
        sendMailToQueue(JsonUtil.createJson(
                EmailNotificationDTO.builder()
                        .type(EmailNotificationType.PORTAL_MAIL.code())
                        .id(emailNotificationId)
                        .build())
        );
    }

    @Override
    public void sendSimpleMailToQueue(String recipients, String message, String subject) {
        sendMailToQueue(JsonUtil.createJson(
                EmailNotificationDTO.builder()
                        .type(EmailNotificationType.SIMPLE_MAIL.code())
                        .message(message)
                        .recipients(recipients)
                        .subject(subject)
                        .build())
        );
    }
}