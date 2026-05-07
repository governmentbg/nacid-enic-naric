package bg.duosoft.nacid.backoffice.core.data.mapper.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationStatusHistoryEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationStatusHistoryDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.*;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ReferenceDataMapper.class, LegalReasonMapper.class })
public abstract class ApplicationStatusHistoryMapper extends BaseObjectMapper<ApplicationStatusHistoryEntity, ApplicationStatusHistoryDTO> {
}
