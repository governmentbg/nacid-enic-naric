package bg.duosoft.nacidservicesbe.mapper.common.person;

import bg.duosoft.nacidcoredata.enums.ForeignIdentifierType;
import bg.duosoft.nacidcoredata.mapper.nomenclature.CountryMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.ReferenceDataMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.SettlementMapper;
import bg.duosoft.nacidfrontofficedto.person.NaturalPersonDTO;
import bg.duosoft.nacidfrontofficedto.person.PersonalIdentifierType;
import bg.duosoft.nacidservicesbe.domain.entity.common.PersonEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 24.10.2022
 * Time: 17:04
 */
@Mapper(componentModel = "spring", uses = {
        CountryMapper.class,
        SettlementMapper.class,
        ReferenceDataMapper.class
})
public abstract class NaturalPersonMapper extends BaseObjectMapper<PersonEntity, NaturalPersonDTO> {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "secondName", source = "middleName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "civilIdType.id", expression = "java(naturalPersonDTO.getPersonalIdType().getCode())")
    @Mapping(target = "foreignIdType", source = "foreignerIdentifierKind")
    @Mapping(target = "foreignIdCountry", source = "foreignerIdentifierCountry")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "originCountry", source = "birthCountry")
    @Mapping(target = "originCity", source = "birthPlace")
    @Mapping(target = "originSettlement", source = "birthSettlement")
    @Mapping(target = "birthDate", source = "dateOfBirth")
    @Mapping(target = "userName", source = "userName")
    @Mapping(target = "citizenship", source = "citizenship")
    @Mapping(target = "legalTypeCode", expression = "java(bg.duosoft.nacidfrontofficedto.person.ApplicantType.NATURAL_PERSON.getLegalTypeCode())")
    @Mapping(target = "legalNatureTypeCode", expression = "java(bg.duosoft.nacidfrontofficedto.person.ApplicantType.NATURAL_PERSON.getLegalNatureTypeCode())")
    @Mapping(target = "humanitarianStatus", source = "humanitarianStatus")
    @Mapping(target = "title", source = "title")
    public abstract PersonEntity toEntity(NaturalPersonDTO naturalPersonDTO);

    @InheritInverseConfiguration
    @Mapping(target = "personalIdType", expression = "java(bg.duosoft.nacidfrontofficedto.person.PersonalIdentifierType.fromCode(person.getCivilIdType().getId()))")
    @Mapping(target = "personalIdTypeName", source = "civilIdType.name")
    public abstract NaturalPersonDTO toDto(PersonEntity person);

    @AfterMapping
    public void afterToEntity(@MappingTarget PersonEntity target, NaturalPersonDTO source){
        if(source.getPersonalIdType().equals(PersonalIdentifierType.DOCUMENT_ID) && source.getForeignerIdentifierKind() != null && ForeignIdentifierType.OFFICIALLY_GENERATED_BY_NACID.getCode().equals(source.getForeignerIdentifierKind().getId())){
            target.setCivilId(source.getPersonalNacidId());
        } else {
            target.setCivilId(source.getPersonalId());
        }
    }

    @AfterMapping
    public void afterToDTO(@MappingTarget NaturalPersonDTO target, PersonEntity source){
        if(target.getPersonalIdType().equals(PersonalIdentifierType.DOCUMENT_ID) && target.getForeignerIdentifierKind() != null && ForeignIdentifierType.OFFICIALLY_GENERATED_BY_NACID.getCode().equals(target.getForeignerIdentifierKind().getId())){
            target.setPersonalNacidId(source.getCivilId());
        } else {
            target.setPersonalId(source.getCivilId());
        }
    }

}
