package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.report.filter;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.report.filter.country.CountryReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.report.filter.apostille.ApostilleApplicationReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.report.filter.article_directive.ArticleDirectiveReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.report.filter.education_level.EducationLevelReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.report.filter.imi_correspondence.ImiCorrespondenceReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.report.filter.prof_experience.ProfExperienceReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.report.filter.recognized_profession.RecognizedProfessionReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.report.filter.service_type_date.ServiceTypeDateReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.report.filter.teacher.TeacherReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.report.filter.training.TrainingReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.common.CommonReportFilterDTO;
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
public class RegprofCommonReportFilterDTO extends CommonReportFilterDTO {


    private CountryReportSectionDTO country;
    private ApostilleApplicationReportSectionDTO apostilleApplication;
    private ServiceTypeDateReportSectionDTO serviceTypeDate;
    private ProfExperienceReportSectionDTO profExperience;
    private TrainingReportSectionDTO training;
    private EducationLevelReportSectionDTO educationLevel;
    private TeacherReportSectionDTO teacher;
    private ArticleDirectiveReportSectionDTO articleDirective;
    private ImiCorrespondenceReportSectionDTO imiCorrespondence;
    private RecognizedProfessionReportSectionDTO recognizedProfession;
}
