package bg.duosoft.nacid.backoffice.core.data.mapper.fo.common;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class FoReferenceDataMapper {
    public abstract bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO toReferenceData(bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO source);
}
