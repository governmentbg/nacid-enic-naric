package bg.duosoft.nacid.backoffice.rudi.be.mapper;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationAttachedDocEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseUniversityExaminationDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.ApplicationAttachedDocMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReferenceDataMapper;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.TrainingCourseUniversityExaminationEntity;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.UniversityExaminationTrainingFormEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Mapper(componentModel = "spring",  uses = {
        ReferenceDataMapper.class,
        UniversityMapper.class,
        CompetentInstitutionMapper.class,
        UniversityExaminationTrainingFormMapper.class,
        ApplicationAttachedDocMapper.class,
        IntegerToBooleanMapper.class,
})
public abstract class TrainingCourseUniversityExaminationMapper extends BaseObjectMapper<TrainingCourseUniversityExaminationEntity, TrainingCourseUniversityExaminationDTO> {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "university", source = "university")
    @Mapping(target = "userCreated", source = "userCreated")
    @Mapping(target = "examinationDate", source = "examinationDate")
    @Mapping(target = "isCommunicated", source = "communicatedFlag")
    @Mapping(target = "isRecognized", source = "recognizedFlag")
    @Mapping(target = "notes", source = "notes")
    @Mapping(target = "trainingLocation", source = "trainingLocation")
    @Mapping(target = "isJointDegree", source = "jointDegreeFlag")
    @Mapping(target = "competentInstitutions", source = "competentInstitutions")
    @Mapping(target = "universityExaminationTrainingForms", source = "universityExaminationTrainingForms")
    @Mapping(target = "attachedDocs", source = "attachedDocs")
    public abstract TrainingCourseUniversityExaminationDTO toDto(TrainingCourseUniversityExaminationEntity entity);

    @AfterMapping
    public void afterToEntity(@MappingTarget TrainingCourseUniversityExaminationEntity target) {
        List<UniversityExaminationTrainingFormEntity> trainingForms = target.getUniversityExaminationTrainingForms();
        if (!CollectionUtils.isEmpty(trainingForms)) {
            trainingForms.stream().forEach(t -> {
                t.setUniversityExamination(target);
            });
        }
    }

}
