package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.mapper;


import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.response.CountryDto;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.entity.CountriesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CountriesMapper extends BaseMapper<CountriesEntity, CountryDto> {
    @Mapping(target = "nameBg", source = "referencedCountry.name")
    public abstract CountryDto toDto(CountriesEntity entity);
}
