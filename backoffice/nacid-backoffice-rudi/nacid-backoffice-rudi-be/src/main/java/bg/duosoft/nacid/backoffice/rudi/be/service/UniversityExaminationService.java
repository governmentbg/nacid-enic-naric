package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseUniversityExaminationDTO;
import bg.duosoft.nacidshared.web.service.CrudServiceBase;

import java.util.List;

public interface UniversityExaminationService extends CrudServiceBase<Integer, TrainingCourseUniversityExaminationDTO> {
    List<TrainingCourseUniversityExaminationDTO> selectUniversityExaminationsByUniversity(Integer universityId);
}
