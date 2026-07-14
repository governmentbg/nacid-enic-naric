package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.main;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.main.RudiMainDataBaseDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@MapperConfig(componentModel = "spring", uses = {
        IntegerToBooleanMapper.class,
})
public interface MainDataMapperConfig {

    @Mappings({
            @Mapping(target = "applicationId", source = "application.id"),
            @Mapping(target = "applicantId", source = "application.applicant.id"),
            @Mapping(target = "representativeId", source = "application.representative.id"),
            @Mapping(target = "representativeCompanyId", source = "application.representativeCompany.id"),
            @Mapping(target = "representativeAuthorizedFlag", source = "representativeAuthorizedFlag"),
            @Mapping(target = "representativeCapacity", source = "application.representativeCapacity"),
            @Mapping(target = "diffDiplomaNamesFlag", source = "application.diffDiplomaNamesFlag"),
            @Mapping(target = "dataAuthenticFlag", source = "application.dataAuthenticFlag"),
            @Mapping(target = "applicantDiplomaNames", source = "application.applicantDiplomaNames"),
            @Mapping(target = "contactAddressId", source = "application.contactAddress.id"),
            @Mapping(target = "officialEmailCommunicationFlag", source = "application.officialEmailCommunicationFlag"),
            @Mapping(target = "personalDataUsageFlag", source = "application.personalDataUsageFlag"),
            @Mapping(target = "applicationNotes", source = "application.applicationNotes"),
            @Mapping(target = "serviceTypeId", source = "application.serviceType.id")
    })
    void toMainDataSectionBase(@MappingTarget RudiMainDataBaseDTO target, RudiApplicationDTO source);

}
