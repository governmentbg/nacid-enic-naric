package bg.duosoft.nacid.backoffice.core.data.mapper.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ChangeRecordLogEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ChangeRecordLogDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ChangeRecordLogMapper extends BaseObjectMapper<ChangeRecordLogEntity, ChangeRecordLogDTO> {
}
