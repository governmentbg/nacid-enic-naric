package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.BaseFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.report.filter.application.ApplicationReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.report.filter.status.StatusReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.report.filter.submission_method.SubmissionMethodReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.report.filter.type.DocumentTypeReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.applicant.legal.LegalApplicantReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.applicant.natural_person.NaturalPersonApplicantReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.application.responsible_user.ApplicationResponsibleUserReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.application.service_type.ServiceTypeReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.application.user_created.ApplicationUserCreatedReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.document.receive_method.DocumentReceiveMethodReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.representative.RepresentativeReportSectionDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: ggeorgiev
 * Date: 03.09.2023
 * Time: 16:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class CommonReportFilterDTO extends BaseFilterDTO {
    private SubmissionMethodReportSectionDTO submissionMethod;
    private StatusReportSectionDTO status;
    private ApplicationReportSectionDTO application;
    private DocumentTypeReportSectionDTO documentType;
    private ServiceTypeReportSectionDTO serviceType;
    private DocumentReceiveMethodReportSectionDTO documentReceiveMethod;
    private ApplicationUserCreatedReportSectionDTO applicationUserCreated;
    private ApplicationResponsibleUserReportSectionDTO applicationResponsibleUser;
    private LegalApplicantReportSectionDTO legalApplicant;
    private RepresentativeReportSectionDTO representative;
    private NaturalPersonApplicantReportSectionDTO naturalPersonApplicant;
    private NaturalPersonNamesReportDTO diplomaName;
}
