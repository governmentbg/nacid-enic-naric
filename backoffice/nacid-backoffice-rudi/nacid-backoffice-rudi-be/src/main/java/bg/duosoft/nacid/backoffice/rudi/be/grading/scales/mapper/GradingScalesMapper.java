package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.mapper;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.response.GradingScaleDto;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.entity.RudiGradingScaleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class GradingScalesMapper extends BaseMapper<RudiGradingScaleEntity, GradingScaleDto> {

    @Mapping(target = "countryCode", source = "country.code")
    public abstract GradingScaleDto toDto(RudiGradingScaleEntity entity);
}
