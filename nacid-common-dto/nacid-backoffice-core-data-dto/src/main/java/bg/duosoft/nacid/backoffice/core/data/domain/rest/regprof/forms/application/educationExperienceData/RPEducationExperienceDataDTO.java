package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.forms.application.educationExperienceData;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.RPProfessionExperienceDocumentDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.RegulatedProfessionExaminationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.forms.application.educationExperienceData.parts.HigherEducationDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.forms.application.educationExperienceData.parts.PostgraduateEducationDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.forms.application.educationExperienceData.parts.SecondaryEducationDataDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RPEducationExperienceDataDTO {
    //COMMON
    private Boolean hasEducation;
    private Boolean hasExperience;
    private Boolean regulatedEducationTrainingFlag;
    private Boolean notRestrictedFlag;
    private CountryDTO applicationCountry;
    private String applicationProfQualification;
    private RegulatedProfessionExaminationDTO regulatedProfessionExamination;
    //EDUCATION
    private String educationType;
    private HigherEducationDataDTO higherEducation;
    private PostgraduateEducationDataDTO postgraduateEducation;
    private SecondaryEducationDataDTO secondaryEducation;
    //EXPERIENCE
    private String professionName;
    private List<RPProfessionExperienceDocumentDTO> documents;
}
