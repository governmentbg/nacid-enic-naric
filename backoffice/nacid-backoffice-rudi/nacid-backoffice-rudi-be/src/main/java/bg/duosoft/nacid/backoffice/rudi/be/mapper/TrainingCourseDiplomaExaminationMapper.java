package bg.duosoft.nacid.backoffice.rudi.be.mapper;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationAttachedDocEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseDiplomaExaminationDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.ApplicationAttachedDocMapper;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.*;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring", uses = {
        IntegerToBooleanMapper.class,
        CompetentInstitutionMapper.class,
})
public abstract class TrainingCourseDiplomaExaminationMapper extends BaseObjectMapper<TrainingCourseDiplomaExaminationEntity, TrainingCourseDiplomaExaminationDTO>  {

    @Autowired
    private ApplicationAttachedDocMapper applicationAttachedDocMapper;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "examinationDate", source = "examinationDate")
    @Mapping(target = "notes", source = "notes")
    @Mapping(target = "isAuthentic", source = "authenticFlag")
    @Mapping(target = "isInstitutionCommunicated", source = "institutionCommunicatedFlag")
    @Mapping(target = "isUniversityCommunicated", source = "universityCommunicatedFlag")
    @Mapping(target = "isFoundInRegister", source = "foundInRegisterFlag")
    @Mapping(target = "isStateApproved", source = "stateApprovedFlag")
    @Mapping(target = "competentInstitution", source = "competentInstitution")
    public abstract TrainingCourseDiplomaExaminationDTO toDto(TrainingCourseDiplomaExaminationEntity e);

    @AfterMapping
    protected void afterToDto(TrainingCourseDiplomaExaminationEntity source, @MappingTarget TrainingCourseDiplomaExaminationDTO target) {
        setAttachedDocsDto(source, target);
    }

    private void setAttachedDocsDto(TrainingCourseDiplomaExaminationEntity source, TrainingCourseDiplomaExaminationDTO target) {
        TrainingCourseEntity trainingCourse = source.getTrainingCourse();
        if (Objects.isNull(trainingCourse)) {
            return;
        }

        List<ApplicationAttachedDocEntity> attachedDocsEntities = trainingCourse.getDiplomaExaminationAttachedDocs();
        if (!CollectionUtils.isEmpty(attachedDocsEntities)) {
            target.setAttachedDocs(applicationAttachedDocMapper.toDtoList(attachedDocsEntities));
        }
    }

}
