package bg.duosoft.nacid.backoffice.core.data.mapper.fo.application;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.RudiApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.SeApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofApplicantDetailsDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class FoApplicationFlagsMapper {

    @Mapping(target = "dataAuthenticFlag", source = "documentsDeclaration")
    @Mapping(target = "personalDataUsageFlag", source = "agreeDataUsage")
    @Mapping(target = "officialEmailCommunicationFlag", source = "agreeMailCorrespondence")
    @BeanMapping(ignoreByDefault = true)
    public abstract void overrideFlagsData(CommonApplicantDetailsDTO source, @MappingTarget ApplicationDTO target);

    @AfterMapping
    protected void afterOverrideFlagsData(CommonApplicantDetailsDTO source, @MappingTarget ApplicationDTO target) {
        if (source instanceof RudiApplicantDetailsDTO rudiApplicantDetails) {
            target.setDiffDiplomaNamesFlag(rudiApplicantDetails.isDiplomaNamesDifferent());
        } else if (source instanceof RegprofApplicantDetailsDTO regprofApplicantDetails) {
            target.setDiffDiplomaNamesFlag(regprofApplicantDetails.isQualificationNamesDifferent());
        } else if (source instanceof SeApplicantDetailsDTO d) {
            target.setDiffDiplomaNamesFlag(d.isDiplomaNamesDifferent());
            target.setOfficialEmailCommunicationFlag(d.getAgreeMailCorrespondence());
        }

    }
}
