package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgAbdocsDocumentEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgAbdocsDocumentDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CfgAbdocsDocumentMapper extends BaseObjectMapper<CfgAbdocsDocumentEntity, CfgAbdocsDocumentDTO> {
}
