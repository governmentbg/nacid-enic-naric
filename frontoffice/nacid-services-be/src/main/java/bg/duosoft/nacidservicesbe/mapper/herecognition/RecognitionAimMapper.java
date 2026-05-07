package bg.duosoft.nacidservicesbe.mapper.herecognition;

import bg.duosoft.nacidcoredata.mapper.nomenclature.ReferenceDataMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.services.wrapper.RecognitionAimWrapperDTO;
import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiRecognitionPurposeEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import bg.duosoft.nacidshareddata.util.ReferenceDataConstants;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 24.10.2022
 * Time: 19:04
 */
@Mapper(componentModel = "spring", uses = {
        ReferenceDataMapper.class,
        IntegerToBooleanMapper.class
})
public abstract class RecognitionAimMapper extends BaseObjectMapper<RudiRecognitionPurposeEntity, ReferenceDataDTO> {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "recognitionPurpose", source = ".")
    @Mapping(target = "id", ignore = true)
    public abstract RudiRecognitionPurposeEntity toEntity(ReferenceDataDTO referenceDataDTO);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "id", source = "recognitionPurpose.pk.id")
    @Mapping(target = "domain", source = "recognitionPurpose.pk.domain")
    @Mapping(target = "domainName", source = "recognitionPurpose.referenceDataDomain.name")
    @Mapping(target = "name", source = "recognitionPurpose.name")
    @Mapping(target = "index", source = "recognitionPurpose.index")
    @Mapping(target = "isActive", source = "recognitionPurpose.active")
    public abstract ReferenceDataDTO toDto(RudiRecognitionPurposeEntity rudiRecognitionPurposeEntity);

    public List<RudiRecognitionPurposeEntity> toEntityListFromWrapper(RecognitionAimWrapperDTO wrapper){
        List<RudiRecognitionPurposeEntity> list = new ArrayList<>();
        if(wrapper != null && wrapper.getRecognitionAim() != null) {
            list = toEntityList(wrapper.getRecognitionAim());
            Optional<RudiRecognitionPurposeEntity> other = list.stream().filter(rp -> rp.getRecognitionPurpose().getPk().getId().equals(ReferenceDataConstants.OTHER_VALUE)).findFirst();
            if(other.isPresent()){
                other.get().setNotes(wrapper.getRecognitionAimOtherDetails());
            }
        }
        return list;
    }

    public RecognitionAimWrapperDTO toDtoWrapperFromList(List<RudiRecognitionPurposeEntity> entityList){
        List<ReferenceDataDTO> list = toDtoList(entityList);
        Optional<RudiRecognitionPurposeEntity> other = entityList.stream().filter(rp -> rp.getRecognitionPurpose().getPk().getId().equals(ReferenceDataConstants.OTHER_VALUE)).findFirst();
        RecognitionAimWrapperDTO wrapper = new RecognitionAimWrapperDTO(list, other.isPresent()? other.get().getNotes(): null);
        return wrapper;
    }

}
