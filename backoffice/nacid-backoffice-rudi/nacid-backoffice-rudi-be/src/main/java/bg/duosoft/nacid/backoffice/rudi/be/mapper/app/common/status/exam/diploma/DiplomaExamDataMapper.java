package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status.exam.diploma;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.examination.diploma.DiplomaExamSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.AttachmentUtils;
import org.mapstruct.*;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class DiplomaExamDataMapper {

    @Mapping(target = "applicationId", source = "application.id")
    @Mapping(target = "examinationDate", source = "trainingCourse.diplomaExamination.examinationDate")
    @Mapping(target = "notes", source = "trainingCourse.diplomaExamination.notes")
    @Mapping(target = "isAuthentic", source = "trainingCourse.diplomaExamination.isAuthentic", defaultValue = "false")
    @Mapping(target = "isInstitutionCommunicated", source = "trainingCourse.diplomaExamination.isInstitutionCommunicated", defaultValue = "false")
    @Mapping(target = "isUniversityCommunicated", source = "trainingCourse.diplomaExamination.isUniversityCommunicated", defaultValue = "false")
    @Mapping(target = "isFoundInRegister", source = "trainingCourse.diplomaExamination.isFoundInRegister", defaultValue = "false")
    @Mapping(target = "isStateApproved", source = "trainingCourse.diplomaExamination.isStateApproved", defaultValue = "false")
    @Mapping(target = "attachedDocs", source = "trainingCourse.diplomaExamination.attachedDocs")
    public abstract DiplomaExamSectionDTO toDiplomaExamSection(RudiApplicationDTO application);

    @InheritInverseConfiguration(name = "toDiplomaExamSection")
    public abstract void overrideApplicationData(DiplomaExamSectionDTO source, @MappingTarget RudiApplicationDTO target);

    @AfterMapping
    public void afterToDiplomaExamSection(RudiApplicationDTO source, @MappingTarget DiplomaExamSectionDTO target) {
        TrainingCourseDTO trainingCourse = source.getTrainingCourse();
        if (Objects.nonNull(trainingCourse)) {

            List<TrainingCourseUniversityDTO> trainingCourseUniversities = trainingCourse.getTrainingCourseUniversities();
            if (!CollectionUtils.isEmpty(trainingCourseUniversities)) {
                List<TrainingCourseUniversityDTO> sortedUniversities = trainingCourseUniversities.stream().sorted(Comparator.comparing(TrainingCourseUniversityDTO::getOrdNum)).toList();

                target.setUniversityNames(sortedUniversities.stream().map(university -> university.getUniversity().getBgName()).collect(Collectors.toList()));
                target.setUniversityCountryIds(sortedUniversities.stream().map(university -> university.getUniversity().getCountry().getId()).collect(Collectors.toList()));
            }

            if (Objects.nonNull(trainingCourse.getDiplomaExamination()) && Objects.nonNull(trainingCourse.getDiplomaExamination().getCompetentInstitution())) {
                target.setCompetentInstitutionId(trainingCourse.getDiplomaExamination().getCompetentInstitution().getId());
            }

            if (Objects.isNull(target.getExaminationDate())) {
                target.setExaminationDate(LocalDate.now());
            }
        }

        AttachmentUtils.sortAttachedDocsData(target.getAttachedDocs());
    }

    @BeforeMapping
    protected void beforeToApplication(DiplomaExamSectionDTO source, @MappingTarget RudiApplicationDTO target) {
        AttachmentUtils.reverseUIAttachedDocs(source.getAttachedDocs());
    }

    @AfterMapping
    public void afterOverride(DiplomaExamSectionDTO source, @MappingTarget RudiApplicationDTO target) {
        TrainingCourseDTO trainingCourse = target.getTrainingCourse();
        if (Objects.nonNull(trainingCourse)) {
            TrainingCourseDiplomaExaminationDTO diplomaExamination = trainingCourse.getDiplomaExamination();
            if (Objects.nonNull(diplomaExamination)) {
                Integer competentInstitutionId = source.getCompetentInstitutionId();

                if (Objects.nonNull(competentInstitutionId)) {
                    diplomaExamination.setCompetentInstitution(new CompetentInstitutionDTO(competentInstitutionId));
                } else {
                    diplomaExamination.setCompetentInstitution(null);
                }

                AttachmentUtils.overrideAttachedDocsData(diplomaExamination.getAttachedDocs());
            }
        }
    }

}
