package bg.duosoft.nacid.backoffice.core.data.mapper.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.VApplicationsByPersonEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationByPersonTableViewDTO;
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
public abstract class VApplicationsByPersonMapper extends BaseObjectMapper<VApplicationsByPersonEntity, ApplicationByPersonTableViewDTO> {

    @Mapping(target = "id", source = "pk.applicationId")
    @Mapping(target = "personRole", source = "pk.personRole")
    @Mapping(target = "dateCreated", source = "dateCreated")
    @Mapping(target = "entryNum", source = "entryNum")
    @Mapping(target = "applicationType", source = "applicationType")
    @Mapping(target = "applicationSubtype", source = "applicationSubtype")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "docflowStatus", source = "docflowStatus")
    public abstract ApplicationByPersonTableViewDTO toDto(VApplicationsByPersonEntity entity);

    @InheritInverseConfiguration
    public abstract VApplicationsByPersonEntity toEntity(ApplicationByPersonTableViewDTO dto);

}
