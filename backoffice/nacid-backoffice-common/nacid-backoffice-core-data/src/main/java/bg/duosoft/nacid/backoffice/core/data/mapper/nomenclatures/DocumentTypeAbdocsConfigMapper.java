package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgDocTypeToAbdocsConfigEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgDocTypeToDocCategoryEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeAbdocsConfigDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDetailDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;

/**
 * User: ggeorgiev
 * Date: 19.08.2022
 * Time: 12:57
 */
@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class, ApplicationTypeMapper.class, ApplicationSubtypeMapper.class})
public abstract class DocumentTypeAbdocsConfigMapper extends BaseObjectMapper<CfgDocTypeToAbdocsConfigEntity, DocumentTypeAbdocsConfigDTO> {
}
