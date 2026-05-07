package bg.duosoft.email.nacidemailproducer.service;

import bg.duosoft.email.nacidemailproducer.domain.core.CEmail;
import bg.duosoft.email.nacidemailproducer.domain.core.CEmailTemporaryKey;
import bg.duosoft.email.nacidemailproducer.domain.core.email_data.*;
import bg.duosoft.email.nacidemailproducer.enums.EmailNotificationEventType;
import bg.duosoft.email.nacidemailproducer.enums.EmailTemporaryKeyType;
import bg.duosoft.email.nacidemailproducer.events.mail.domain.SimpleMailEvent;
import bg.duosoft.email.nacidemailproducer.events.mail.publisher.MailEventPublisher;
import bg.duosoft.email.nacidemailproducer.property.EmailProducerPropertyAccess;
import bg.duosoft.email.nacidemailproducer.service.event.MailEventService;
import bg.duosoft.email.nacidemailproducer.utils.DateUtils;
import bg.duosoft.email.nacidemailproducer.utils.JsonUtil;
import bg.duosoft.email.nacidemailproducer.utils.TemporaryKeyUtils;
import bg.duosoft.nacidfrontofficedto.user.NacidUserDetailsDTO;
import bg.duosoft.nacidkeycloakservices.service.KeycloakUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {

    private final MailEventPublisher mailEventPublisher;
    private final EmailTemporaryKeyService temporaryKeyService;
    private final EmailProducerPropertyAccess propertyAccess;
    private final MailEventService mailEventService;
    private final KeycloakUserService keycloakUserService;

    @Override
    public void sendSimpleMail(String recipients, String subject, String message) {
        mailEventPublisher.publish(SimpleMailEvent.newEvent(this, recipients, message, subject));
    }

    @Override
    public void sendAccountActivationMail(CAccountActivationEmailData data) {
        String key = TemporaryKeyUtils.generateKey();
        String link = data.getUri().replace("{key}", TemporaryKeyUtils.encodeKey(key));
        Pair<Date, Date> keyDatesPair = saveTemporaryKey(key, EmailTemporaryKeyType.ACCOUNT_ACTIVATION, propertyAccess.getKeyTimeoutAccountActivation(), data.getUsername(), null);
        String expirationDate = DateUtils.formatDateTime(keyDatesPair.getSecond());

        Map<String, String> params = data.createParamsMap();
        params.put("{link}", link);
        params.put("{expirationDate}", expirationDate);

        mailEventService.triggerEvent(EmailNotificationEventType.AccountActivation_EndUser, params, Collections.singletonList(data.getEmail()));
    }

    @Override
    public void sendChangeEmailMail(CChangeMailEmailData data) {
        String key = TemporaryKeyUtils.generateKey();
        String link = data.getUri().replace("{key}", TemporaryKeyUtils.encodeKey(key));
        Pair<Date, Date> keyDatesPair = saveTemporaryKey(key, EmailTemporaryKeyType.CHANGE_EMAIL, propertyAccess.getKeyTimeoutChangeEmail(), data.getUsername(), JsonUtil.createJson(new CEmail(data.getNewEmail())));
        String expirationDate = DateUtils.formatDateTime(keyDatesPair.getSecond());

        Map<String, String> params = data.createParamsMap();
        params.put("{link}", link);
        params.put("{expirationDate}", expirationDate);

        mailEventService.triggerEvent(EmailNotificationEventType.ChangeEmail_EndUser, params, Collections.singletonList(data.getNewEmail()));
    }

    @Override
    public void sendResetPasswordEmail(CResetPasswordEmailData data) {
        String key = TemporaryKeyUtils.generateKey();
        String link = data.getUri().replace("{key}", TemporaryKeyUtils.encodeKey(key));
        Pair<Date, Date> keyDatesPair = saveTemporaryKey(key, EmailTemporaryKeyType.RESET_PASSWORD, propertyAccess.getKeyTimeoutResetPassword(), data.getUsername(), null);
        String expirationDate = DateUtils.formatDateTime(keyDatesPair.getSecond());

        Map<String, String> params = data.createParamsMap();
        params.put("{link}", link);
        params.put("{expirationDate}", expirationDate);

        mailEventService.triggerEvent(EmailNotificationEventType.ResetPassword_EndUser, params, Collections.singletonList(data.getEmail()));
    }

    @Override
    public void sendNewPortalAccountEmail(CNewPortalAccountEmailData data) {
        mailEventService.triggerEvent(EmailNotificationEventType.NewPortalAccount_Admin, data.createParamsMap(), null);
    }

    @Override
    public void sendForgottenUsernameEmail(CForgottenUsernameEmailData data) {
        mailEventService.triggerEvent(EmailNotificationEventType.ForgottenUsername_EndUser, data.createParamsMap(), Collections.singletonList(data.getEmail()));
    }

    @Override
    public void sendChangePasswordEmail(CChangePasswordEmailData data) {
        mailEventService.triggerEvent(EmailNotificationEventType.ChangePassword_EndUser, data.createParamsMap(), Collections.singletonList(data.getEmail()));
    }

    @Override
    public void sendApplicationSubmittedMail(CApplicationSubmittedEmailData data) {
        mailEventService.triggerEvent(EmailNotificationEventType.ApplicationSubmitted_EndUser, data.createParamsMap(), Collections.singletonList(data.getEmail()));
    }

    @Override
    public void sendApplicationAcceptedMail(CApplicationAcceptedEmailData data) {
        mailEventService.triggerEvent(EmailNotificationEventType.ApplicationAccepted_EndUser, data.createParamsMap(), Collections.singletonList(data.getEmail()));
    }

    @Override
    public void sendApplicationRevertedDraftMail(CApplicationRevertedDraftEmailData data) {
        mailEventService.triggerEvent(EmailNotificationEventType.ApplicationRevertedDraft_EndUser, data.createParamsMap(), Collections.singletonList(data.getEmail()));
    }

    @Override
    public void sendLibservAppSubmissionMail(CLibservAppSubmissionEmailData data) {
        mailEventService.triggerEvent(EmailNotificationEventType.LibservAppSubmission_Admin, data.createParamsMap(), null);
    }

    @Override
    public void sendPaymentsErrorAdminMail(CPaymentsErrorAdminEmailData data) {
        mailEventService.triggerEvent(EmailNotificationEventType.PaymentsError_Admin, data.createParamsMap(), null);
    }

    @Override
    public void sendResponsibleUserChangeMail(CChangeResponsibleUserEmailData data) {
        String targetUsername = data.getTargetUsername();
        String sourceUsername = data.getSourceUsername();

        NacidUserDetailsDTO targetUser = keycloakUserService.getUserByUsername(targetUsername);
        if (Objects.isNull(targetUser)) {
            log.error("===[MAIL PRODUCER]=== Cannot find user with username {}" + targetUsername);
            throw new RuntimeException("Cannot find user with username " + targetUsername);
        }

        NacidUserDetailsDTO sourceUser = keycloakUserService.getUserByUsername(sourceUsername);
        if (Objects.isNull(sourceUser)) {
            log.error("===[MAIL PRODUCER]=== Cannot find user with username {}" + sourceUsername);
            throw new RuntimeException("Cannot find user with username " + sourceUsername);
        }

        String email = targetUser.getEmail();
        if (!StringUtils.hasText(email)) {
            log.error("===[MAIL PRODUCER]=== Email is empty for user {}" + targetUsername);
            throw new RuntimeException("Email is empty for user " + targetUsername);
        }

        Map<String, String> params = new LinkedHashMap<>();
        params.put("targetUserFullName", targetUser.getFirstAndLastName());
        params.put("sourceUserFullName", sourceUser.getFirstAndLastName());
        params.put("appEntryNumber", data.getAppEntryNumber());

        mailEventService.triggerEvent(EmailNotificationEventType.ResponsibleUserChange_EndUser, params, Collections.singletonList(targetUser.getEmail()));
    }

    @Override
    public void sendLiabilityChangeNotificationMail(CLiabilityNotificationEmailData data, EmailNotificationEventType type) {
        NacidUserDetailsDTO targetUser = keycloakUserService.getUserByUsername(data.getTargetUserName());
        if (Objects.isNull(targetUser)) {
            log.error("===[MAIL PRODUCER]=== Cannot find user with username {}" + data.getTargetUserName());
            throw new RuntimeException("Cannot find user with username " + data.getTargetUserName());
        }
        Map<String, String> params = new LinkedHashMap<>();
        params.put("targetUserFullName", targetUser.getFirstAndLastName());
        params.put("foReferenceNumber", data.getFoReferenceNumber());
        params.put("amount", data.getAmount());

        mailEventService.triggerEvent(type, params, Collections.singletonList(targetUser.getEmail()));
    }

    @Override
    public void sendPaidLiabilityNotificationMail(CPaidLiabilityNotificationEmailData data) {
        NacidUserDetailsDTO targetUser = keycloakUserService.getUserByUsername(data.getTargetUserName());
        if (Objects.isNull(targetUser)) {
            log.error("===[MAIL PRODUCER]=== Cannot find user with username {}" + data.getTargetUserName());
            throw new RuntimeException("Cannot find user with username " + data.getTargetUserName());
        }
        Map<String, String> params = new LinkedHashMap<>();
        params.put("targetUserFullName", targetUser.getFirstAndLastName());
        params.put("boReferenceNumber", data.getBoReferenceNumber());
        mailEventService.triggerEvent(EmailNotificationEventType.PaidLiabilityNotification_EndUser, params, Collections.singletonList(targetUser.getEmail()));
    }

    private Pair<Date, Date> saveTemporaryKey(String key, EmailTemporaryKeyType type, int keyTimeout, String username, String extraData) {
        Pair<Date, Date> period = TemporaryKeyUtils.calculateExpirationPeriod(keyTimeout);
        temporaryKeyService.saveKey(CEmailTemporaryKey.builder().type(type).key(key).user(username).createdDate(period.getFirst()).expirationDate(period.getSecond()).extraData(extraData).build());
        return period;
    }

    @Override
    public void sendCorrespondenceNotificationMail(CCorrespondenceNotificationEmailData data) {
        mailEventService.triggerEvent(EmailNotificationEventType.CorrespondenceNotification_EndUser, data.createParamsMap(), Collections.singletonList(data.getEmail()));
    }

    @Override
    public void sendARUniRegisterUpdatedMail(CARUniRegisterUpdateEmailData data) {
        mailEventService.triggerEvent(EmailNotificationEventType.ARUniRegisterUpdated_Admin, data.createParamsMap(), null);
    }

    @Override
    public void sendOriginalDocumentsNotificationMail(CSEOriginalDocumentsNotificationEmailData data) {
        mailEventService.triggerEvent(EmailNotificationEventType.OriginalProvidedDocumentsNotification_EndUser, data.createParamsMap(), Collections.singletonList(data.getEmail()));
    }

    @Override
    public void sendAdditionalDocumentsReceivedMail(CAdditionalDocumentsReceivedData data) {
        NacidUserDetailsDTO targetUser = keycloakUserService.getUserByUsername(data.getTargetUsername());
        if (Objects.isNull(targetUser)) {
            log.error("===[MAIL PRODUCER]=== Cannot find user with username {}" + data.getTargetUsername());
            throw new RuntimeException("Cannot find user with username " + data.getTargetUsername());
        }

        mailEventService.triggerEvent(EmailNotificationEventType.AdditionalDocumentsReceived_EndUser, data.createParamsMap(), Collections.singletonList(targetUser.getEmail()));
    }
}
