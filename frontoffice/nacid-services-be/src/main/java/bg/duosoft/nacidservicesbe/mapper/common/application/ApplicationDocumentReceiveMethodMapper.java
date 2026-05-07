package bg.duosoft.nacidservicesbe.mapper.common.application;

import bg.duosoft.nacidcoredata.mapper.nomenclature.DocumentReceiveMethodMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.ReferenceDataMapper;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationDocumentReceiveMethodDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationDocumentReceiveMethodEntity;
import bg.duosoft.nacidservicesbe.mapper.common.address.ReceiverAddressMapper;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.05.2024
 * Time: 15:50
 */
@Mapper(componentModel = "spring", uses = {
        ReferenceDataMapper.class,
        DocumentReceiveMethodMapper.class,
        ReceiverAddressMapper.class,
        IntegerToBooleanMapper.class,
})
public abstract class ApplicationDocumentReceiveMethodMapper extends BaseObjectMapper<ApplicationDocumentReceiveMethodEntity, ApplicationDocumentReceiveMethodDTO> {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "documentReceiveMethod", source = "resultReceive")
    @Mapping(target = "documentRecipientAddress", source = "receiverAddress")
    public abstract ApplicationDocumentReceiveMethodEntity toEntity(ApplicationDocumentReceiveMethodDTO applicationDocumentReceiveMethodDTO);

    @InheritInverseConfiguration
    public abstract ApplicationDocumentReceiveMethodDTO toDto(ApplicationDocumentReceiveMethodEntity applicationDocumentReceiveMethodEntity);


}
