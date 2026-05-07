package bg.duosoft.nacidservicesbe.mapper.common.person;

import bg.duosoft.nacidcoredata.mapper.nomenclature.CountryMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.SettlementMapper;
import bg.duosoft.nacidfrontofficedto.person.CompanyDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.PersonEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.11.2022
 * Time: 16:07
 */
@Mapper(componentModel = "spring", uses = {
        SettlementMapper.class,
        CountryMapper.class
})
public abstract class CompanyMapper extends BaseObjectMapper<PersonEntity, CompanyDTO> {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "civilId", source = "companyIdentifier")
    @Mapping(target = "civilIdType.id", expression = "java(companyDTO.getCompanyIdentifierType().getCode())")
    @Mapping(target = "legalName", source = "companyName")
    @Mapping(target = "originCity", source = "companyCity")
    @Mapping(target = "originSettlement", source = "companySettlement")
    @Mapping(target = "originCountry", source = "companyCountry")
    @Mapping(target = "legalTypeCode", expression = "java(bg.duosoft.nacidfrontofficedto.person.ApplicantType.COMPANY.getLegalTypeCode())")
    @Mapping(target = "legalNatureTypeCode", expression = "java(bg.duosoft.nacidfrontofficedto.person.ApplicantType.COMPANY.getLegalNatureTypeCode())")
    public abstract PersonEntity toEntity(CompanyDTO companyDTO);

    @InheritInverseConfiguration
    @Mapping(target = "companyIdentifierTypeName", source = "civilIdType.name")
    public abstract CompanyDTO toDto(PersonEntity personEntity);

}
