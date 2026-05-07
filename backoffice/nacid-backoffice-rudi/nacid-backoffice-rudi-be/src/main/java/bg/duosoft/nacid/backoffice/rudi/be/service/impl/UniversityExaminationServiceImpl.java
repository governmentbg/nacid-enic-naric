package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseUniversityExaminationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.TrainingCourseUniversityExaminationMapper;
import bg.duosoft.nacid.backoffice.rudi.be.repository.TrainingCourseUniversityExaminationRepository;
import bg.duosoft.nacid.backoffice.rudi.be.service.UniversityExaminationService;
import bg.duosoft.nacid.backoffice.rudi.be.validator.UniversityExaminationValidator;
import bg.duosoft.nacidshared.web.service.impl.CrudServiceBaseImpl;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UniversityExaminationServiceImpl extends CrudServiceBaseImpl<Integer, TrainingCourseUniversityExaminationDTO> implements UniversityExaminationService {
    private final TrainingCourseUniversityExaminationRepository universityExaminationRepository;
    private final TrainingCourseUniversityExaminationMapper universityExaminationMapper;
    private final UniversityExaminationValidator universityExaminationValidator;

    @Override
    protected TrainingCourseUniversityExaminationRepository getRepository() {
        return universityExaminationRepository;
    }

    @Override
    protected TrainingCourseUniversityExaminationMapper getMapper() {
        return universityExaminationMapper;
    }

    @Override
    protected Validator<TrainingCourseUniversityExaminationDTO> getValidator() {
        return universityExaminationValidator;
    }

    @Override
    public List<TrainingCourseUniversityExaminationDTO> selectUniversityExaminationsByUniversity(Integer universityId) {
        return universityExaminationMapper.toDtoList(universityExaminationRepository.selectUniversityExaminationsByUniversity(universityId));
    }

}
