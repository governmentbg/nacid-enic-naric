package bg.duosoft.nacid.backoffice.rudi.be.mapper.app;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.InsertStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.RudiStatusDataBaseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class AppStatusDataInsertStatusMapper {
    @Mapping(target = "applicationId", source = "applicationId")
    @Mapping(target = "statusId", source = "status.id")
    @Mapping(target = "legalReasonId", source = "legalReason.id")
    @Mapping(target = "docflowStatusId", source = "docflowStatus.id")
    public abstract InsertStatusDTO toInsertActionDTO(RudiStatusDataBaseDTO application);

}
