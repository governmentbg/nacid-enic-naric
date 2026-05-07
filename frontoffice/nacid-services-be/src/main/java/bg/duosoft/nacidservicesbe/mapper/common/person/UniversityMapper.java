package bg.duosoft.nacidservicesbe.mapper.common.person;

import bg.duosoft.nacidcoredata.mapper.nomenclature.CountryMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.SettlementMapper;
import bg.duosoft.nacidfrontofficedto.person.UniversityDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.PersonEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 18.01.2023
 * Time: 13:12
 */
@Mapper(componentModel = "spring", uses = {
        CountryMapper.class,
        SettlementMapper.class,
})
public abstract class UniversityMapper extends BaseObjectMapper<PersonEntity, UniversityDTO> {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "civilId", source = "universityIdentifier")
    @Mapping(target = "civilIdType.id", expression = "java(universityDTO.getCompanyIdentifierType().getCode())")
    @Mapping(target = "legalName", source = "universityName")
    @Mapping(target = "originCountry", source = "universityCountry")
    @Mapping(target = "originSettlement", source = "universitySettlement")
    @Mapping(target = "legalTypeCode", expression = "java(bg.duosoft.nacidfrontofficedto.person.ApplicantType.UNIVERSITY.getLegalTypeCode())")
    @Mapping(target = "legalNatureTypeCode", expression = "java(bg.duosoft.nacidfrontofficedto.person.ApplicantType.UNIVERSITY.getLegalNatureTypeCode())")
    public abstract PersonEntity toEntity(UniversityDTO universityDTO);

    @InheritInverseConfiguration
    @Mapping(target = "universityIdentifierTypeName", source = "civilIdType.name")
    public abstract UniversityDTO toDto(PersonEntity personEntity);
}
