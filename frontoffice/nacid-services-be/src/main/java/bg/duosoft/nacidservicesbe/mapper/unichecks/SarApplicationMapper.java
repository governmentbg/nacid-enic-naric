package bg.duosoft.nacidservicesbe.mapper.unichecks;

import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksEducationDetailsDTO;
import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiSarApplicationEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.01.2023
 * Time: 16:55
 */
@Mapper(componentModel = "spring", uses = {
        IntegerToBooleanMapper.class
})
public abstract class SarApplicationMapper extends BaseObjectMapper<RudiSarApplicationEntity, UniChecksEducationDetailsDTO> {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "statuteFlag", source = "statute")
    @Mapping(target = "authenticityFlag", source = "authenticity")
    @Mapping(target = "recommendationFlag", source = "recommendation")
    @Mapping(target = "outgoingNumber", source = "nacidOutgoingNumber")
    @Mapping(target = "internalNumber", source = "applicantIncomingNumber")
    public abstract RudiSarApplicationEntity toEntity(UniChecksEducationDetailsDTO uniChecksEducationDetailsDTO);

    @InheritInverseConfiguration
    public abstract UniChecksEducationDetailsDTO toDto(RudiSarApplicationEntity rudiSarApplicationEntity);

    public static void setSarDetailsToMainEducationDetailsDto(UniChecksEducationDetailsDTO mainOne, UniChecksEducationDetailsDTO sarOne){
        mainOne.setStatute(sarOne.getStatute());
        mainOne.setAuthenticity(sarOne.getAuthenticity());
        mainOne.setRecommendation(sarOne.getRecommendation());

        mainOne.setNacidOutgoingNumber(sarOne.getNacidOutgoingNumber());
        mainOne.setApplicantIncomingNumber(sarOne.getApplicantIncomingNumber());
    }

}
