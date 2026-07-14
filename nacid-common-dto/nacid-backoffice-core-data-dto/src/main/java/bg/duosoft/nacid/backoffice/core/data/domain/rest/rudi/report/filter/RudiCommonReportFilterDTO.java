package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.application.type.ApplicationTypeReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.commission.CommissionReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.common.CommonReportFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.diploma.DiplomaReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.diploma.DiplomaSpecialityReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.diploma.RecognizedDiplomaSpecialityReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.diploma.diploma_owner.DiplomaOwnerSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.institution.TrainingInstitutionReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.university.UniversityReportSectionDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RudiCommonReportFilterDTO extends CommonReportFilterDTO {
    private ApplicationTypeReportSectionDTO applicationType;
    private CommissionReportSectionDTO commission;
    private DiplomaReportSectionDTO diploma;
    private UniversityReportSectionDTO university;
    private TrainingInstitutionReportSectionDTO trainingInstitution;
    private DiplomaSpecialityReportSectionDTO diplomaSpeciality;
    private RecognizedDiplomaSpecialityReportSectionDTO recognizedDiplomaSpeciality;
    private DiplomaOwnerSectionDTO diplomaOwner;
}
