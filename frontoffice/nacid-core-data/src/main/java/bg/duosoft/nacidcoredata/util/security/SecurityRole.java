package bg.duosoft.nacidcoredata.util.security;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 13.07.2022
 * Time: 14:00
 */
public interface SecurityRole {

    String SERVICES_ACCESS = "FO_services_access";
    String SERVICES_ACCEPT = "FO_services_accept";
    String SERVICES_ORIGINAL_WAITING_EDIT = "FO_isOriginalWaiting_edit";
    String CORRESPONDENCE_CREATE = "FO_correspondence_create";
    String SERVICES_STATUS_MODIFY = "FO_applicationStatuses_edit";
    String SERVICES_PAID_MODIFY = "FO_applicationPaid_edit";
    String FILE_STORE_ENTRY_EDIT = "FO_fileStoreEntry_edit";
    String FILE_STORE_ENTRY_DELETE = "FO_fileStoreEntry_delete";
    String ADMIN_CONSOLE_ACCESS = "FO_ac_access";
    String ADMIN_CONSOLE_EDIT = "FO_ac_edit";
    String CONTENT_MANAGEMENT_EDIT = "FO_contentManagement_edit";
    String CONTACTS_ACCESS = "FO_acContacts_access";
    String LAW_ACCESS = "FO_acLaw_access";
    String EMAIL_TEMPLATES_ACCESS = "FO_acEmailTemplates_access";
    String EMAIL_TEMPLATES_DELETE = "FO_acEmailTemplates_delete";
    String EMAIL_TEMPLATES_EDIT = "FO_acEmailTemplates_edit";
    String EMAIL_NOTIFICATIONS_ACCESS = "FO_acEmailNotifications_access";
    String EMAIL_NOTIFICATIONS_DELETE = "FO_acEmailNotifications_delete";
    String EMAIL_NOTIFICATIONS_RESEND = "FO_acEmailNotifications_resend";
    String SITEMAP_ACCESS = "FO_acSitemap_access";
    String HOME_PAGE_CATEGORY = "FO_acHomePageCategories_access";
    String SERVICE_DEFINITION_ACCESS = "FO_acServicesDescription_access";
    String SERVICE_DEFINITION_EDIT = "FO_acServicesDescription_edit";
    String GLOBAL_MESSAGE_ACCESS = "FO_acGlobalMessage_access";
    String FEEDBACK_ACCESS = "FO_acFeedbacks_access";
    String FEEDBACK_EDIT = "FO_acFeedbacks_edit";
    String FEEDBACK_DELETE = "FO_acFeedbacks_delete";
    String GROUPS_AND_ROLES_ACCESS = "FO_acGroupsAndRoles_access";
    String USERS_ACCESS = "FO_acUsers_access";
    String ROLES_EDIT = "FO_acRoles_edit";
    String GROUPS_CREATE = "FO_acGroups_create";
    String GROUPS_EDIT = "FO_acGroups_edit";
    String GROUPS_DELETE = "FO_acGroups_delete";
    String USERS_EDIT = "FO_acUsers_edit";
    String JOINED_NOMENCLATURES_ACCESS = "FO_nomJoinedNomenclatures_access";
    String JOINED_NOMENCLATURES_EDIT = "FO_nomJoinedNomenclatures_edit";
    String NATIONAL_UNIVERSITY_ACCESS = "FO_nomNationalUniversities_access";
    String NATIONAL_UNIVERSITY_EDIT = "FO_nomNationalUniversities_edit";
    String ACADREC_UNI_ENTRY_REQUEST_CREATE = "FO_acadrecUniEntryRequest_create";
    String ACADREC_UNI_ENTRY_REQUEST_ACCEPT = "FO_acadrecUniEntryRequest_accept";
    String REGPROF_APOSTILLE_APPLICATION_CREATE = "FO_regprofApostilleApplication_create";
    String EMPLOYEE_APP_SUBMIT = "FO_employee_app_submit";
}
