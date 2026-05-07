package bg.duosoft.email.nacidemailproducer.enums;

import lombok.Getter;

@Getter
public enum EmailNotificationEventType {

    AccountActivation_EndUser("AccountActivation_EndUser", MailTemplate.ACCOUNT_ACTIVATION),
    ForgottenUsername_EndUser("ForgottenUsername_EndUser", MailTemplate.FORGOTTEN_USERNAME),
    ResetPassword_EndUser("ResetPassword_EndUser", MailTemplate.RESET_PASSWORD),
    ChangePassword_EndUser("ChangePassword_EndUser", MailTemplate.CHANGE_PASSWORD),
    ChangeEmail_EndUser("ChangeEmail_EndUser", MailTemplate.CHANGE_EMAIL),
    ApplicationSubmitted_EndUser("ApplicationSubmitted_EndUser", MailTemplate.APPLICATION_SUBMITTED),
    CorrespondenceNotification_EndUser("CorrespondenceNotification_EndUser", MailTemplate.CORRESPONDENCE_NOTIFICATION),
    ApplicationAccepted_EndUser("ApplicationAccepted_EndUser", MailTemplate.APPLICATION_ACCEPTED),
    ApplicationRevertedDraft_EndUser("ApplicationRevertedDraft_EndUser", MailTemplate.APPLICATION_REVERTED_DRAFT),
    ResponsibleUserChange_EndUser("ResponsibleUserChange_EndUser", MailTemplate.RESPONSIBLE_USER_CHANGE),
    MigratedAccountsResetPassword_EndUser("MigratedAccountsResetPassword_EndUser", MailTemplate.MIGRATED_ACCOUNTS_RESET_PASSWORD),

    InsertLiabilityNotification_EndUser("InsertLiabilityNotification_EndUser", MailTemplate.INSERT_LIABILITY_NOTIFICATION),
    DeleteLiabilityNotification_EndUser("DeleteLiabilityNotification_EndUser", MailTemplate.DELETE_LIABILITY_NOTIFICATION),
    UpdateLiabilityNotification_EndUser("UpdateLiabilityNotification_EndUser", MailTemplate.UPDATE_LIABILITY_NOTIFICATION),
    PaidLiabilityNotification_EndUser("PaidLiabilityNotification_EndUser", MailTemplate.PAID_LIABILITY_NOTIFICATION),

    NewPortalAccount_Admin("NewPortalAccount_Admin", MailTemplate.NEW_PORTAL_ACCOUNT_ADMIN),
    LibservAppSubmission_Admin("LibservAppSubmission_Admin", MailTemplate.LIBSERV_APP_SUBMISSION_ADMIN),
    PaymentsError_Admin("PaymentsError_Admin", MailTemplate.LIBSERV_APP_SUBMISSION_ADMIN),
    ARUniRegisterUpdated_Admin("ARUniRegisterUpdated_Admin", MailTemplate.AR_UNI_REGISTER_UPDATED_ADMIN),
    AdditionalDocumentsReceived_EndUser("AdditionalDocumentsReceived_EndUser", MailTemplate.ADDITIONAL_DOCUMENTS_RECEIVED),
    OriginalProvidedDocumentsNotification_EndUser("OriginalProvidedDocumentsNotification_EndUser", MailTemplate.ORIGINAL_PROVIDED_DOCUMENTS_NOTIFICATION);


    private final String code;
    private final MailTemplate template;

    EmailNotificationEventType(String code, MailTemplate template) {
        this.code = code;
        this.template = template;
    }
}
