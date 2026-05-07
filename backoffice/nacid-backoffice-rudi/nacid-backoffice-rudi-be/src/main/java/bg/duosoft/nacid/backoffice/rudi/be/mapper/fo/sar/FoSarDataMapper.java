package bg.duosoft.nacid.backoffice.rudi.be.mapper.fo.sar;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.SarApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.fo.nomenclature.FoLanguageMapper;
import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksEducationDetailsDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public abstract class FoSarDataMapper {

    @Mapping(target = "outgoingNumber", source = "nacidOutgoingNumber")
    @Mapping(target = "internalNumber", source = "applicantIncomingNumber")
    @Mapping(target = "isStatute", source = "statute")
    @Mapping(target = "isAuthenticity", source = "authenticity")
    @Mapping(target = "isRecommendation", source = "recommendation")
    @BeanMapping(ignoreByDefault = true)
    public abstract SarApplicationDTO toSarApplicationData(UniChecksEducationDetailsDTO source);

}
