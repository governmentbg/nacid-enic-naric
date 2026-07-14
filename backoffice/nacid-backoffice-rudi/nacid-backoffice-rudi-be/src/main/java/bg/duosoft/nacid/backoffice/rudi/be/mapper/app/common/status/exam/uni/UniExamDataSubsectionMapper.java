package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status.exam.uni;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.TrainingForm;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseUniversityExaminationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.UniversityExaminationTrainingFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.examination.university.UniExamSubsectionDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.AttachmentUtils;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import org.mapstruct.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class UniExamDataSubsectionMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "isCommunicated", source = "isCommunicated")
    @Mapping(target = "isRecognized", source = "isRecognized")
    @Mapping(target = "isJointDegree", source = "isJointDegree")
    @Mapping(target = "university.id", source = "university.id")
    @Mapping(target = "examinationDate", source = "examinationDate")
    @Mapping(target = "competentInstitutions", source = "competentInstitutions")
    @Mapping(target = "notes", source = "notes")
    public abstract void toUniversityExamination(UniExamSubsectionDTO source, @MappingTarget TrainingCourseUniversityExaminationDTO universityExamination);

    @InheritInverseConfiguration(name = "toUniversityExamination")
    @Mapping(target = "university.bgName", source = "university.bgName")
    @Mapping(target = "university.orgName", source = "university.orgName")
    @Mapping(target = "university.country", source = "university.country")
    @Mapping(target = "university.address", source = "university.address")
    @Mapping(target = "trainingLocationId", source = "trainingLocation.id")
    public abstract UniExamSubsectionDTO toUniExamSubsection(TrainingCourseUniversityExaminationDTO source, @Context Boolean clearIdFields);

    @AfterMapping
    public void afterToUniExamSubsection(TrainingCourseUniversityExaminationDTO source, @MappingTarget UniExamSubsectionDTO target, @Context Boolean clearIdFields) {
        if (!CollectionUtils.isEmpty(source.getUniversityExaminationTrainingForms())) {
            target.setTrainingForms(source.getUniversityExaminationTrainingForms().stream().map(form -> form.getTrainingForm().getId()).collect(Collectors.toList()));

            UniversityExaminationTrainingFormDTO otherTrainingForm = source.getUniversityExaminationTrainingForms().stream().filter(form -> TrainingForm.OTHER.code().equals(form.getTrainingForm().getId())).findFirst().orElse(null);
            if (Objects.nonNull(otherTrainingForm)) {
                target.setOtherTrainingFormNote(otherTrainingForm.getNotes());
            }
        } else {
            target.setTrainingForms(new ArrayList<>());
        }

        target.setAttachedDocs(source.getAttachedDocs());
        AttachmentUtils.sortAttachedDocsData(target.getAttachedDocs());

        clearIdFields(target, clearIdFields);
    }

    private void clearIdFields(UniExamSubsectionDTO target, Boolean clearIdFields) {
        if (clearIdFields) {
            target.setId(null);
            target.setExaminationDate(LocalDate.now());
            target.setAttachedDocs(new ArrayList<>());
        }
    }

    @AfterMapping
    public void afterOverride(UniExamSubsectionDTO source, @MappingTarget TrainingCourseUniversityExaminationDTO target) {
        overrideSourceData(source);

        if (StringUtils.hasText(source.getTrainingLocationId())) {
            target.setTrainingLocation(new ReferenceDataDTO(ReferenceDataDomain.UNIVERSITY_EXAMINATION_TRAINING_LOCATION.domain(), source.getTrainingLocationId()));
        }

        if (Objects.isNull(target.getId())) {
            target.setUserCreated(SecurityUtils.getUsername());
        }

        List<String> trainingForms = source.getTrainingForms();
        List<UniversityExaminationTrainingFormDTO> trainingCourseTrainingForms = new ArrayList<>();
        if (!CollectionUtils.isEmpty(trainingForms)) {
            for (String code : trainingForms) {
                UniversityExaminationTrainingFormDTO trainingForm = null;
                List<UniversityExaminationTrainingFormDTO> universityTrainingForms = target.getUniversityExaminationTrainingForms();
                if (!CollectionUtils.isEmpty(universityTrainingForms)) {
                    trainingForm = universityTrainingForms.stream().filter(o -> o.getTrainingForm().getId().equals(code)).findFirst().orElse(null);
                }


                if (Objects.isNull(trainingForm)) {
                    trainingForm = new UniversityExaminationTrainingFormDTO();
                    trainingForm.setTrainingForm(new ReferenceDataDTO(ReferenceDataDomain.TRAINING_FORM.domain(), code));
                }

                if (TrainingForm.OTHER.code().equals(code)) {
                    trainingForm.setNotes(source.getOtherTrainingFormNote());
                } else {
                    trainingForm.setNotes(null);
                }
                trainingCourseTrainingForms.add(trainingForm);
            }
        }
        target.setUniversityExaminationTrainingForms(trainingCourseTrainingForms);
        target.setAttachedDocs(source.getAttachedDocs());

        AttachmentUtils.reverseUIAttachedDocs(target.getAttachedDocs());
        AttachmentUtils.overrideAttachedDocsData(target.getAttachedDocs());
    }

    public void overrideSourceData(UniExamSubsectionDTO source) {
       if (Objects.nonNull(source) && !CollectionUtils.isEmpty(source.getTrainingForms())) {
           boolean hasOtherNote = source.getTrainingForms().stream().anyMatch(form -> TrainingForm.OTHER.code().equals(form));
           if (!hasOtherNote) {
               source.setOtherTrainingFormNote(null);
           }
       }
    }

}
