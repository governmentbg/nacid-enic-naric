package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgAbdocsActionEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgAbdocsActionDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CfgAbdocsActionMapper extends BaseObjectMapper<CfgAbdocsActionEntity, CfgAbdocsActionDTO> {
}
