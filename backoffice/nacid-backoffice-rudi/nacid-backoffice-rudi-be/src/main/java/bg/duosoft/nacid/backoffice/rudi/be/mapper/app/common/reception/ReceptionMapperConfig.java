package bg.duosoft.nacid.backoffice.rudi.be.mapper.app.common.reception;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.reception.RudiBaseReceptionDTO;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@MapperConfig(componentModel = "spring", uses = {
        IntegerToBooleanMapper.class,
})
public interface ReceptionMapperConfig {

    @Mappings({
            @Mapping(target = "applicantId", source = "application.applicant.id"),
            @Mapping(target = "representativeId", source = "application.representative.id"),
            @Mapping(target = "representativeCompanyId", source = "application.representativeCompany.id"),
            @Mapping(target = "representativeAuthorizedFlag", source = "representativeAuthorizedFlag"),
            @Mapping(target = "representativeCapacity", source = "application.representativeCapacity"),
            @Mapping(target = "diffDiplomaNamesFlag", source = "application.diffDiplomaNamesFlag"),
            @Mapping(target = "applicantDiplomaNames", source = "application.applicantDiplomaNames"),
            @Mapping(target = "contactAddressId", source = "application.contactAddress.id"),
            @Mapping(target = "officialEmailCommunicationFlag", source = "application.officialEmailCommunicationFlag"),
            @Mapping(target = "serviceTypeId", source = "application.serviceType.id"),
            @Mapping(target = "baseUniversityId", source = "trainingCourse.baseUniversity.id"),
            @Mapping(target = "manualTempUniName", source = "trainingCourse.manualTempUniName"),
            @Mapping(target = "personalDataUsageFlag", source = "application.personalDataUsageFlag"),
            @Mapping(target = "dataAuthenticFlag", source = "application.dataAuthenticFlag")
    })
    void toReceptionBase(@MappingTarget RudiBaseReceptionDTO target, RudiApplicationDTO source);

}
