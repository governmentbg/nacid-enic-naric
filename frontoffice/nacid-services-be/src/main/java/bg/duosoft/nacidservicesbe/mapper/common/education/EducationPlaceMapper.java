package bg.duosoft.nacidservicesbe.mapper.common.education;

import bg.duosoft.nacidcoredata.mapper.nomenclature.CountryMapper;
import bg.duosoft.nacidfrontofficedto.services.common.education.EducationPlaceDTO;
import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiTrainingLocationEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 26.10.2022
 * Time: 13:12
 */
@Mapper(componentModel = "spring", uses = { CountryMapper.class })
public abstract class EducationPlaceMapper extends BaseObjectMapper<RudiTrainingLocationEntity, EducationPlaceDTO> {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "country", source = "country")
    @Mapping(target = "city", source = "city")
    public abstract RudiTrainingLocationEntity toEntity(EducationPlaceDTO educationPlaceDTO);

    @InheritInverseConfiguration
    public abstract EducationPlaceDTO toDto(RudiTrainingLocationEntity rudiTrainingLocationEntity);
}
