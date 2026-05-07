package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgDocTypeToAppStatusEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeAppStatusDetailDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import java.util.Objects;

/**
 * User: ggeorgiev
 * Date: 05.04.2023
 * Time: 15:23
 */
@Mapper(componentModel = "spring", uses = {DocumentTypeMapper.class, ApplicationTypeMapper.class, ReferenceDataMapper.class})
public abstract class CfgDocTypeToAppStatusMapper extends BaseObjectMapper<CfgDocTypeToAppStatusEntity, DocumentTypeAppStatusDetailDTO> {


    @AfterMapping
    protected void afterMapping(DocumentTypeAppStatusDetailDTO source, @MappingTarget CfgDocTypeToAppStatusEntity target) {
        if (Objects.nonNull(target.getStatus())) {
            target.getStatus().getPk().setDomain(ReferenceDataDomain.APPLICATION_STATUS.domain());
        }
    }
}
