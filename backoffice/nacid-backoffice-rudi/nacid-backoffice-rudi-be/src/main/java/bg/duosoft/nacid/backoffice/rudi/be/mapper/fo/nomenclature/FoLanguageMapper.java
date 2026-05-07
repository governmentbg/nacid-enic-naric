package bg.duosoft.nacid.backoffice.rudi.be.mapper.fo.nomenclature;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class FoLanguageMapper {
    public abstract bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.LanguageDTO toBoProfGroup(bg.duosoft.nacidfrontofficedto.nomenclature.LanguageDTO source);
}
