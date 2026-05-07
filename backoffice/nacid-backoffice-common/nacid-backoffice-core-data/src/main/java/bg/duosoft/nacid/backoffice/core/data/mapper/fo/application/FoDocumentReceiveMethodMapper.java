package bg.duosoft.nacid.backoffice.core.data.mapper.fo.application;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class FoDocumentReceiveMethodMapper {
    public abstract bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentReceiveMethodDTO toDocumentReceiveMethod(bg.duosoft.nacidfrontofficedto.nomenclature.DocumentReceiveMethodDTO source);
}
