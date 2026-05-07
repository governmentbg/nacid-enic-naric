package bg.duosoft.nacidservicesbe.mapper.publicaccess;

import bg.duosoft.nacidcoredata.mapper.nomenclature.ReferenceDataMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidservicesbe.domain.entity.lib.PublicAccessInfoFormEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.08.2023
 * Time: 15:40
 */
@Mapper(componentModel = "spring", uses = {
        ReferenceDataMapper.class
})
public abstract class PublicAccessInfoFormMapper extends BaseObjectMapper<PublicAccessInfoFormEntity, ReferenceDataDTO> {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "infoForm", source = ".")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "publicAccessApplication", ignore = true)
    public abstract PublicAccessInfoFormEntity toEntity(ReferenceDataDTO referenceDataDTO);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    public abstract ReferenceDataDTO toDto(PublicAccessInfoFormEntity publicAccessInfoFormEntity);

}
