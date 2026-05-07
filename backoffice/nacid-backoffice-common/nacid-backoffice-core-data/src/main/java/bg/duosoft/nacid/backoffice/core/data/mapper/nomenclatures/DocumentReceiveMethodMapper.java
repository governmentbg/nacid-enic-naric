package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.DocumentReceiveMethodEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentReceiveMethodDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * User: ggeorgiev
 * Date: 15.07.2022
 * Time: 14:32
 */
@Mapper(/*config = BaseNomenclatureMapperConfig.class,*/ componentModel = "spring", uses = {IntegerToBooleanMapper.class, ReferenceDataMapper.class})
public abstract class DocumentReceiveMethodMapper extends BaseNomenclatureMapper<DocumentReceiveMethodEntity, DocumentReceiveMethodDTO> {
    @Mapping(target = "hasDocumentRecipient", source = "documentRecipientFlag")
    @Mapping(target = "eservicesRequirePaymentReceipt", source = "eservicesRequirePaymentReceiptFlag")
    @Mapping(target = "isActive", source = "active")
    @Mapping(target = "index", source = "index")
    @Mapping(target = "defaultFlag", source = "defaultFlag")
    @Mapping(target = "crfCode", source = "crfCode")
    public abstract DocumentReceiveMethodDTO toDto(DocumentReceiveMethodEntity e);
}
