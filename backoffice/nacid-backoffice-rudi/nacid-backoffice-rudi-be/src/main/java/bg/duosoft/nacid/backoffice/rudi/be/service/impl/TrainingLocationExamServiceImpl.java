package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.InsertStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationStatusType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.TrainingLocationExaminationUniversityDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.UniversitySimpleDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.examination.training_location.TrainingLocationExamSectionDTO;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status.exam.location.TrainingLocationExamDataMapper;
import bg.duosoft.nacid.backoffice.rudi.be.service.RudiStatusService;
import bg.duosoft.nacid.backoffice.rudi.be.service.TrainingLocationExamService;
import bg.duosoft.nacid.backoffice.rudi.be.service.RudiApplicationService;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.ValidationScope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class TrainingLocationExamServiceImpl implements TrainingLocationExamService {
    private final TrainingLocationExamDataMapper trainingLocationExamDataMapper;
    private final RudiApplicationService rudiApplicationService;
    private final RudiStatusService rudiStatusService;

    @Override
    public TrainingLocationExamSectionDTO saveTrainingLocationExamData(TrainingLocationExamSectionDTO trainingLocationExamination, RudiApplicationDTO rudiApplication) {
        trainingLocationExamDataMapper.overrideApplicationData(trainingLocationExamination, rudiApplication);
        rudiApplicationService.save(rudiApplication, ValidationScope.TRAINING_LOCATION_EXAMINATION);

        if (trainingLocationExamination.getIsLegitimate()) {
            if (!ApplicationStatusType.LEGITIMATE_BY_TRAINING_LOCATION.code().equals(rudiApplication.getApplication().getStatus().getId())) {
                rudiStatusService.insertRudiStatus(rudiApplication.getApplication().getId(), InsertStatusDTO.builder().applicationId(rudiApplication.getApplication().getId()).statusId(ApplicationStatusType.LEGITIMATE_BY_TRAINING_LOCATION.code()).build());
                trainingLocationExamination.setIsStatusUpdated(true);
            }
        }

        return trainingLocationExamination;
    }

    @Override
    public List<TrainingLocationExaminationUniversityDataDTO> selectUniversitiesSubsectionInfo(RudiApplicationDTO rudiApplication) {
        List<TrainingLocationExaminationUniversityDataDTO> universityData = new ArrayList<>();

        TrainingCourseDTO trainingCourse = rudiApplication.getTrainingCourse();
        if (Objects.nonNull(trainingCourse)) {
            List<TrainingCourseUniversityDTO> trainingCourseUniversities = trainingCourse.getTrainingCourseUniversities();

            if (!CollectionUtils.isEmpty(trainingCourseUniversities)) {
                List<TrainingCourseUniversityDTO> sortedUniversities = trainingCourseUniversities.stream().sorted(Comparator.comparing(TrainingCourseUniversityDTO::getOrdNum)).toList();
                List<TrainingCourseUniversityExaminationDTO> trainingCourseUniversityExaminations = trainingCourse.getTrainingCourseUniversityExaminations();

                for (TrainingCourseUniversityDTO trainingCourseUniversity : sortedUniversities) {
                    UniversityDTO university = trainingCourseUniversity.getUniversity();
                    UniversitySimpleDTO simpleUniversity = UniversitySimpleDTO.builder().id(university.getId()).bgName(university.getBgName()).country(university.getCountry()).address(university.getAddress()).build();

                    ReferenceDataDTO trainingLocation = null;
                    if (!CollectionUtils.isEmpty(trainingCourseUniversityExaminations)) {
                        TrainingCourseUniversityExaminationDTO trainingCourseUniversityExamination = trainingCourseUniversityExaminations.stream().filter(examination -> university.getId().equals(examination.getUniversity().getId())).findFirst().orElse(null);
                        if (Objects.nonNull(trainingCourseUniversityExamination)) {
                            trainingLocation = trainingCourseUniversityExamination.getTrainingLocation();
                        }
                    }
                    universityData.add(TrainingLocationExaminationUniversityDataDTO.builder().university(simpleUniversity).uniExamTrainingLocation(trainingLocation).build());
                }
            }
        }
        return universityData;
    }
}
