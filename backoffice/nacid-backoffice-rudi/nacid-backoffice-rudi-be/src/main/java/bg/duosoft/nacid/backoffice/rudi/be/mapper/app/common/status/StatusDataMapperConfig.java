package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.status;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.RudiStatusDataBaseDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;

@MapperConfig(componentModel = "spring", uses = {
        IntegerToBooleanMapper.class,
})
public interface StatusDataMapperConfig {

    @BeanMapping(ignoreByDefault = true)
    @Mappings({
            @Mapping(target = "applicationId", source = "application.id"),
            @Mapping(target = "status", source = "application.status"),
            @Mapping(target = "submittedDocs", source = "submittedDocs"),
            @Mapping(target = "docflowStatus", source = "application.docflowStatus"),
            @Mapping(target = "legalReason", source = "legalReason")
    })
    void toStatusDataSectionBase(@MappingTarget RudiStatusDataBaseDTO target, RudiApplicationDTO source);

}
