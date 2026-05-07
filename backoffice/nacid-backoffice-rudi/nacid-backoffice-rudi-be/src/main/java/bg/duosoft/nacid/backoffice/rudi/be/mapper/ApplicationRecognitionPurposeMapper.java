package bg.duosoft.nacid.backoffice.rudi.be.mapper;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.ApplicationRecognitionPurposeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReferenceDataMapper;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.ApplicationRecognitionPurposeEntity;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.RudiApplicationEntity;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.TrainingCourseEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {
        ReferenceDataMapper.class
})
public abstract class ApplicationRecognitionPurposeMapper extends BaseObjectMapper<ApplicationRecognitionPurposeEntity, ApplicationRecognitionPurposeDTO> {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "applicationId", source = "application.id")
    @Mapping(target = "recognitionPurpose", source = "recognitionPurpose")
    @Mapping(target = "notes", source = "notes")
    public abstract ApplicationRecognitionPurposeDTO toDto(ApplicationRecognitionPurposeEntity entity);

    @InheritInverseConfiguration
    public abstract ApplicationRecognitionPurposeEntity toEntity(ApplicationRecognitionPurposeDTO dto);

    @AfterMapping
    protected void afterToEntity(ApplicationRecognitionPurposeDTO source, @MappingTarget ApplicationRecognitionPurposeEntity target) {

    }

    @AfterMapping
    protected void afterToDto(ApplicationRecognitionPurposeEntity source, @MappingTarget ApplicationRecognitionPurposeDTO target) {

    }

}
