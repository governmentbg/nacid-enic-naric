package bg.duosoft.nacidcoredata.enums;

import bg.duosoft.nacidcoredata.util.security.SecurityRole;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Getter
public enum ContentManagementId {
    Contacts("contacts", null, List.of(SecurityRole.CONTACTS_ACCESS)),
    Law("law", null, List.of(SecurityRole.LAW_ACCESS)),
    Sitemap("sitemap", null, List.of(SecurityRole.SITEMAP_ACCESS)),
    HomePageCategoryControl("homePageCategoryControl", null, List.of(SecurityRole.HOME_PAGE_CATEGORY)),
    HigherEducationRecognition("serviceDefinition.higherEducationRecognition", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    SecondaryEducationRecognition("serviceDefinition.secondaryEducationRecognition", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    SecondaryEducationVerificationLetter("serviceDefinition.secondaryEduVerificationLetter", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    SecondaryEducationOfficialNote("serviceDefinition.secondaryEduOfficialNote", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    RudiAdditionalDocs("serviceDefinition.rudiAdditionalDoc", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    RegprofAdditionalDocs("serviceDefinition.regprofAdditionalDoc", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    LibservAdditionalDocs("serviceDefinition.libservAdditionalDoc", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    SeAdditionalDocs("serviceDefinition.seAdditionalDoc", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    RudiDuplicate("serviceDefinition.rudiDuplicate", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    RegprofDuplicate("serviceDefinition.regprofDuplicate", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    SeDuplicate("serviceDefinition.seDuplicate", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    DoctorateDegreesDoctor("serviceDefinition.doctorateDegrees.doctor", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    DoctorateDegreesDoctorOfScience("serviceDefinition.doctorateDegrees.doctorOfScience", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    UniChecksAcademicStatus("serviceDefinition.uniChecks.academicStatus", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    UniChecksDocumentAuthenticity("serviceDefinition.uniChecks.documentAuthenticity", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    UniChecksIssueRecommendation("serviceDefinition.uniChecks.issueRecommendation", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    NonRegulatedProfessions("serviceDefinition.nonRegulatedProfessions", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    PublicAccess("serviceDefinition.publicAccess", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    Suggestion("serviceDefinition.suggestion", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    Signal("serviceDefinition.signal", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    OfficialNotesResearchProject("serviceDefinition.officialNotes.researchProject", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    OfficialNotesScientificPaper("serviceDefinition.officialNotes.scientificPaper", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    ServiceDefinitionOfficialNotesDissertationThesis("serviceDefinition.officialNotes.dissertationThesis", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    OfficialNotesAcademicPosition("serviceDefinition.officialNotes.academicPosition", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    InquiryImpactFactor("serviceDefinition.inquiry.impactFactor", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    InquiryPublicationCitings("serviceDefinition.inquiry.publicationCitings", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    AnalyticProducts("serviceDefinition.analyticProducts", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    BibliographicReferencesNacidDbs("serviceDefinition.bibliographicReferences.nacidDbs", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    BibliographicReferencesForeignDbs("serviceDefinition.bibliographicReferences.foreignDbs", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    DocumentDeliveryLibraries("serviceDefinition.documentDelivery.libraries", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    documentDeliveryNacid("serviceDefinition.documentDelivery.nacid", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    ARUniEntryRequest("serviceDefinition.arUniEntryRequest", null, List.of(SecurityRole.SERVICE_DEFINITION_EDIT)),
    AdminConsole("adminConsole", List.of(SecurityRole.ADMIN_CONSOLE_ACCESS), List.of(SecurityRole.ADMIN_CONSOLE_EDIT));

    ContentManagementId(String code) {
        this.code = code;
    }

    ContentManagementId(String code, List<String> accessRolesOnView, List<String> accessRolesOnEdit) {
        this.code = code;
        this.accessRolesOnView = accessRolesOnView;
        this.accessRolesOnEdit = accessRolesOnEdit;
    }

    private final String code;
    private List<String> accessRolesOnView;

    private List<String> accessRolesOnEdit;

    public static ContentManagementId selectByCode(String code) {
        if (!StringUtils.hasText(code)) {
            return null;
        }

        return Arrays.stream(ContentManagementId.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

}
