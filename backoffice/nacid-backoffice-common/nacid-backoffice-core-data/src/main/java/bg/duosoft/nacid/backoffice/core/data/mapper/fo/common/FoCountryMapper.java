package bg.duosoft.nacid.backoffice.core.data.mapper.fo.common;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class FoCountryMapper {
    public abstract bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO toCountry(bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO source);
}
