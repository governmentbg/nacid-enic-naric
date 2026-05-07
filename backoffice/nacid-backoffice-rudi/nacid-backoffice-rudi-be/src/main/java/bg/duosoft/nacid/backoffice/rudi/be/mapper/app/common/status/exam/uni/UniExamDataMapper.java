package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status.exam.uni;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.TrainingForm;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.UniversitySimpleDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.examination.university.UniExamSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.examination.university.UniExamSubsectionDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.AttachmentUtils;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class UniExamDataMapper {

    @Autowired
    private UniExamDataSubsectionMapper examDataSubsectionMapper;

    @Mapping(target = "applicationId", source = "application.id")
    public abstract UniExamSectionDTO toUniExamSection(RudiApplicationDTO application);

    public abstract void overrideApplicationData(UniExamSubsectionDTO source, @MappingTarget RudiApplicationDTO target);

    @BeforeMapping
    private void beforeToApplication(UniExamSubsectionDTO source, @MappingTarget RudiApplicationDTO target) {
        AttachmentUtils.reverseUIAttachedDocs(source.getAttachedDocs());
    }

    @AfterMapping
    public void afterOverride(UniExamSubsectionDTO source, @MappingTarget RudiApplicationDTO target) {
       TrainingCourseDTO trainingCourse = target.getTrainingCourse();

       if (Objects.isNull(trainingCourse)) {
           trainingCourse = new TrainingCourseDTO();
       }

        List<TrainingCourseUniversityExaminationDTO> trainingCourseUniversityExaminations = trainingCourse.getTrainingCourseUniversityExaminations();
       if (Objects.isNull(trainingCourseUniversityExaminations)) {
           trainingCourseUniversityExaminations = new ArrayList<>();
       }

        Integer universityId = source.getUniversity().getId();
        TrainingCourseUniversityExaminationDTO trainingCourseExamination = trainingCourseUniversityExaminations.stream().filter(ex -> universityId.equals(ex.getUniversity().getId())).findFirst().orElse(null);
        if (Objects.nonNull(trainingCourseExamination)) {
           examDataSubsectionMapper.toUniversityExamination(source, trainingCourseExamination);
        } else {
            TrainingCourseUniversityExaminationDTO trainingCourseUniversityExamination = new TrainingCourseUniversityExaminationDTO();
            examDataSubsectionMapper.toUniversityExamination(source, trainingCourseUniversityExamination);
            trainingCourseUniversityExaminations.add(trainingCourseUniversityExamination);
        }
    }

    @AfterMapping
    public void afterToUniExamSection(RudiApplicationDTO source, @MappingTarget UniExamSectionDTO target) {
        List<UniExamSubsectionDTO> examinations = new ArrayList<>();

        TrainingCourseDTO trainingCourse = source.getTrainingCourse();
        if (Objects.nonNull(trainingCourse)) {
            List<TrainingCourseUniversityExaminationDTO> universityExaminations = trainingCourse.getTrainingCourseUniversityExaminations();

            List<TrainingCourseUniversityDTO> trainingCourseUniversities = trainingCourse.getTrainingCourseUniversities();
            if (!CollectionUtils.isEmpty(trainingCourseUniversities)) {
                List<TrainingCourseUniversityDTO> sortedUniversities = trainingCourseUniversities.stream().sorted(Comparator.comparing(TrainingCourseUniversityDTO::getOrdNum)).toList();

                for (TrainingCourseUniversityDTO university : sortedUniversities) {
                    appendExaminationSubsections(examinations, university.getUniversity(), universityExaminations);
                }
            }

        }
        target.setExaminations(examinations);
    }

    private void appendExaminationSubsections(List<UniExamSubsectionDTO> examinations, UniversityDTO university, List<TrainingCourseUniversityExaminationDTO> universityExaminations) {
        if (Objects.nonNull(university)) {
            UniExamSubsectionDTO subsection = new UniExamSubsectionDTO();
            subsection.setUniversity(new UniversitySimpleDTO(university.getId(), university.getBgName(), university.getOrgName(), university.getCountry(), university.getAddress()));

            TrainingCourseUniversityExaminationDTO selectedExamination = universityExaminations.stream().filter((examination) -> university.getId().equals(examination.getUniversity().getId())).findFirst().orElse(null);
            if (Objects.nonNull(selectedExamination)) {
                subsection.setId(selectedExamination.getId());
                subsection.setExaminationDate(selectedExamination.getExaminationDate());
                subsection.setIsCommunicated(selectedExamination.getIsCommunicated());
                subsection.setIsRecognized(selectedExamination.getIsRecognized());
                subsection.setIsJointDegree(selectedExamination.getIsJointDegree());
                subsection.setNotes(selectedExamination.getNotes());
                subsection.setTrainingLocationId(Objects.nonNull(selectedExamination.getTrainingLocation()) ? selectedExamination.getTrainingLocation().getId() : null);

                List<UniversityExaminationTrainingFormDTO> universityExaminationTrainingForms = selectedExamination.getUniversityExaminationTrainingForms();
                if (CollectionUtils.isEmpty(universityExaminationTrainingForms)) {
                    universityExaminationTrainingForms = new ArrayList<>();
                }
                subsection.setTrainingForms(universityExaminationTrainingForms.stream().map((form) -> form.getTrainingForm().getId()).collect(Collectors.toList()));

                UniversityExaminationTrainingFormDTO otherTrainingForm = universityExaminationTrainingForms.stream().filter((form) -> TrainingForm.OTHER.code().equals(form.getTrainingForm().getId())).findFirst().orElse(null);
                if (Objects.nonNull(otherTrainingForm)) {
                    subsection.setOtherTrainingFormNote(otherTrainingForm.getNotes());
                }
                subsection.setCompetentInstitutions(selectedExamination.getCompetentInstitutions());
                subsection.setAttachedDocs(selectedExamination.getAttachedDocs());
                AttachmentUtils.sortAttachedDocsData(subsection.getAttachedDocs());
            }
            examinations.add(subsection);
        }
    }
}
