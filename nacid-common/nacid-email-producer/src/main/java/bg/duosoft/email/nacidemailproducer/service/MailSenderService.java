package bg.duosoft.email.nacidemailproducer.service;

import bg.duosoft.email.nacidemailproducer.domain.core.email_data.*;
import bg.duosoft.email.nacidemailproducer.enums.EmailNotificationEventType;

public interface MailSenderService {

    void sendSimpleMail(String recipients, String subject, String message);

    void sendAccountActivationMail(CAccountActivationEmailData data);

    void sendChangeEmailMail(CChangeMailEmailData data);

    void sendResetPasswordEmail(CResetPasswordEmailData data);

    void sendNewPortalAccountEmail(CNewPortalAccountEmailData data);

    void sendForgottenUsernameEmail(CForgottenUsernameEmailData data);

    void sendChangePasswordEmail(CChangePasswordEmailData data);

    void sendApplicationSubmittedMail(CApplicationSubmittedEmailData data);

    void sendApplicationAcceptedMail(CApplicationAcceptedEmailData data);

    void sendApplicationRevertedDraftMail(CApplicationRevertedDraftEmailData data);

    void sendLibservAppSubmissionMail(CLibservAppSubmissionEmailData data);

    void sendPaymentsErrorAdminMail(CPaymentsErrorAdminEmailData data);

    void sendResponsibleUserChangeMail(CChangeResponsibleUserEmailData data);

    void sendLiabilityChangeNotificationMail(CLiabilityNotificationEmailData data, EmailNotificationEventType type);

    void sendPaidLiabilityNotificationMail(CPaidLiabilityNotificationEmailData data);
    void sendCorrespondenceNotificationMail(CCorrespondenceNotificationEmailData data);

    void sendARUniRegisterUpdatedMail(CARUniRegisterUpdateEmailData data);

    void sendOriginalDocumentsNotificationMail(CSEOriginalDocumentsNotificationEmailData data);
    void sendAdditionalDocumentsReceivedMail(CAdditionalDocumentsReceivedData data);

}
