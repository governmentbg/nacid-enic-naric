package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class RegprofApplicationDTO implements Serializable {

    private ApplicationDTO application;
    private RPTrainingExperienceDTO trainingExperience;
    private LocalDate endDate;
    private String imiCorrespondence;
    private Boolean apostilleApplicationFlag;
    private Integer apostilleParentDocumentId;
    private CountryDTO applicationCountry;
    private String applicationProfQualification;
    private RegulatedProfessionExaminationDTO regulatedProfessionExamination;
}
