package bg.duosoft.nacid.backoffice.core.data.mapper.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationTableViewDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ApplicationSubtypeMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ApplicationTypeMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReferenceDataMapper;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        ReferenceDataMapper.class,
        ApplicationTypeMapper.class,
        ApplicationSubtypeMapper.class,
})
public abstract class ApplicationTableViewMapper extends BaseObjectMapper<ApplicationEntity, ApplicationTableViewDTO> {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "dateCreated", source = "dateCreated")
    @Mapping(target = "applicationType", source = "applicationType")
    @Mapping(target = "applicationSubtype", source = "applicationSubtype")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "docflowStatus", source = "docflowStatus")
    @Mapping(target = "entryNum", source = "entryNumber")
    public abstract ApplicationTableViewDTO toDto(ApplicationEntity entity);

    @InheritInverseConfiguration
    public abstract ApplicationEntity toEntity(ApplicationTableViewDTO dto);

}
