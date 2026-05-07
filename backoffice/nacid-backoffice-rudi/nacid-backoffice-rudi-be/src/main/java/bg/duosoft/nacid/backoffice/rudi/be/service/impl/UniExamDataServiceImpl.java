package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.InsertStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationStatusType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.examination.university.UniExamSubsectionDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.AttachmentUtils;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status.exam.uni.UniExamDataMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status.exam.uni.UniExamDataSubsectionMapper;
import bg.duosoft.nacid.backoffice.rudi.be.service.RudiStatusService;
import bg.duosoft.nacid.backoffice.rudi.be.service.UniExamDataService;
import bg.duosoft.nacid.backoffice.rudi.be.service.RudiApplicationService;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.ValidationScope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class UniExamDataServiceImpl implements UniExamDataService {
    private final UniExamDataMapper uniExamDataMapper;
    private final UniExamDataSubsectionMapper uniExamDataSubsectionMapper;
    private final RudiApplicationService rudiApplicationService;
    private final RudiStatusService rudiStatusService;

    @Override
    public UniExamSubsectionDTO saveUniExamData(UniExamSubsectionDTO universityExamination, RudiApplicationDTO rudiApplication) {
        uniExamDataMapper.overrideApplicationData(universityExamination, rudiApplication);
        RudiApplicationDTO savedApplication = rudiApplicationService.save(rudiApplication, ValidationScope.UNIVERSITY_EXAMINATION);
        boolean isStatusUpdated = updateStatus(universityExamination, rudiApplication);
        return selectUpdatedUniExam(universityExamination, savedApplication, isStatusUpdated);
    }

    private boolean updateStatus(UniExamSubsectionDTO universityExamination, RudiApplicationDTO rudiApplication) {
        boolean isStatusUpdated = false;
        if (universityExamination.getIsRecognized()) {
            if (!ApplicationStatusType.LEGITIMATE_BY_HEADQUARTERS.code().equals(rudiApplication.getApplication().getStatus().getId())) {
                rudiStatusService.insertRudiStatus(rudiApplication.getApplication().getId(), InsertStatusDTO.builder().applicationId(rudiApplication.getApplication().getId()).statusId(ApplicationStatusType.LEGITIMATE_BY_HEADQUARTERS.code()).build());
                isStatusUpdated = true;
            }
        }
        return isStatusUpdated;
    }

    @Override
    public void overrideAttachedDocsWithOriginal(UniExamSubsectionDTO uniExamSubsection, RudiApplicationDTO application) {
        if (Objects.nonNull(application)) {
            TrainingCourseDTO trainingCourse = application.getTrainingCourse();

            if (Objects.nonNull(trainingCourse)) {
                List<TrainingCourseUniversityExaminationDTO> trainingCourseUniversityExaminations = trainingCourse.getTrainingCourseUniversityExaminations();

                if (!CollectionUtils.isEmpty(trainingCourseUniversityExaminations)) {
                    Integer universityId = uniExamSubsection.getUniversity().getId();
                    TrainingCourseUniversityExaminationDTO existingExam = trainingCourseUniversityExaminations.stream().filter(e -> e.getUniversity().getId().equals(universityId)).findFirst().orElse(null);

                    if (Objects.nonNull(existingExam)) {
                        uniExamSubsection.setAttachedDocs(existingExam.getAttachedDocs());
                        AttachmentUtils.sortAttachedDocsData(uniExamSubsection.getAttachedDocs());
                    }
                }
            }
        }
    }

    private UniExamSubsectionDTO selectUpdatedUniExam(UniExamSubsectionDTO universityExamination, RudiApplicationDTO savedApplication, boolean isStatusUpdated) {
        List<TrainingCourseUniversityExaminationDTO> trainingCourseUniversityExaminations = savedApplication.getTrainingCourse().getTrainingCourseUniversityExaminations();
        TrainingCourseUniversityExaminationDTO trainingCourseUniversityExamination = trainingCourseUniversityExaminations.stream().filter(ex -> universityExamination.getUniversity().getId().equals(ex.getUniversity().getId())).findFirst().orElse(null);

        if (Objects.isNull(trainingCourseUniversityExamination)) {
            throw new RuntimeException("University examination was not saved ! University ID: " + universityExamination.getUniversity().getId());
        }

        UniExamSubsectionDTO uniExamSubsection = uniExamDataSubsectionMapper.toUniExamSubsection(trainingCourseUniversityExamination, false);
        uniExamSubsection.setIsStatusUpdated(isStatusUpdated);
        return uniExamSubsection;
    }
}
