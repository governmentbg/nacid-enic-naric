package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.TrainingLocationExaminationUniversityDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.examination.training_location.TrainingLocationExamSectionDTO;

import java.util.List;

public interface TrainingLocationExamService {
    TrainingLocationExamSectionDTO saveTrainingLocationExamData(TrainingLocationExamSectionDTO trainingLocationExamination, RudiApplicationDTO rudiApplication);
    List<TrainingLocationExaminationUniversityDataDTO> selectUniversitiesSubsectionInfo(RudiApplicationDTO rudiApplication);

}
