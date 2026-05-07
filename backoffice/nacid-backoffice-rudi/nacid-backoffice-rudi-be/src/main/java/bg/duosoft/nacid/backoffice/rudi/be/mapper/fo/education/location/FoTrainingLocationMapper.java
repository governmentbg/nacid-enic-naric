package bg.duosoft.nacid.backoffice.rudi.be.mapper.fo.education.location;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingLocationDTO;
import bg.duosoft.nacidfrontofficedto.services.common.education.EducationPlaceDTO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;

@Mapper(componentModel = "spring")
public abstract class FoTrainingLocationMapper {

    @Mapping(target = "country", source = "country")
    @Mapping(target = "city", source = "city")
    public abstract TrainingLocationDTO toTrainingLocation(EducationPlaceDTO source);

    @InheritConfiguration(name = "toTrainingLocation")
    @IterableMapping(nullValueMappingStrategy = RETURN_DEFAULT)
    public abstract List<TrainingLocationDTO> toTrainingLocationList(List<EducationPlaceDTO> sourceList);

}
