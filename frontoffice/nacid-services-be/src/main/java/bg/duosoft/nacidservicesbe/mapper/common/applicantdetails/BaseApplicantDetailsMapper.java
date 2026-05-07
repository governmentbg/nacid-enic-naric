package bg.duosoft.nacidservicesbe.mapper.common.applicantdetails;

import bg.duosoft.nacidcoredata.mapper.nomenclature.DocumentReceiveMethodMapper;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacidservicesbe.mapper.common.address.ContactAddressMapper;
import bg.duosoft.nacidservicesbe.mapper.common.address.ReceiverAddressMapper;
import bg.duosoft.nacidservicesbe.mapper.common.person.NaturalPersonMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.11.2022
 * Time: 13:49
 */
@MapperConfig(componentModel = "spring",
        uses = {
                ContactAddressMapper.class,
                ReceiverAddressMapper.class,
                ServicesApplicantMapper.class,
                NaturalPersonMapper.class,
                DocumentReceiveMethodMapper.class,
                IntegerToBooleanMapper.class,
        }
)
public interface BaseApplicantDetailsMapper {

    @Mappings({
            @Mapping(target = "id", source = "applicationId"),
            @Mapping(target = "applicant", source = "applicant"),
            @Mapping(target = "representative", source = "representative"),
            @Mapping(target = "contactAddress", source = "contactAddress"),
            @Mapping(target = "personalDataUsageFlag", source = "agreeDataUsage"),
            @Mapping(target = "dataAuthenticFlag", source = "documentsDeclaration"),
            @Mapping(target = "representativeCapacity", source = "representativeCapacity"),
            @Mapping(target = "applicantTitleBefore", source = "applicantTitleBefore"),
            @Mapping(target = "applicantTitleAfter", source = "applicantTitleAfter")
    })
    void baseApplicantDetailsMapping(@MappingTarget ApplicationEntity application, CommonApplicantDetailsDTO applicantDetailsDTO);
}
