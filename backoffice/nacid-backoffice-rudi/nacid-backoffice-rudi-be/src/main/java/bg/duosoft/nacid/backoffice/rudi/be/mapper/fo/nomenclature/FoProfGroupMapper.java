package bg.duosoft.nacid.backoffice.rudi.be.mapper.fo.nomenclature;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class FoProfGroupMapper {
    public abstract bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ProfGroupDTO toBoProfGroup(bg.duosoft.nacidfrontofficedto.nomenclature.ProfGroupDTO source);
}
