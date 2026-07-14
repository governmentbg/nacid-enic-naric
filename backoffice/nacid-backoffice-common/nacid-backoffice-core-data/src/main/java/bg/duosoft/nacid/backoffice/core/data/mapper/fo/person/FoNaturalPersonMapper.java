package bg.duosoft.nacid.backoffice.core.data.mapper.fo.person;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ForeignIdType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.LegalType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.person.NaturalPersonDTO;
import org.mapstruct.*;

import java.util.Objects;

@Mapper(componentModel = "spring")
public abstract class FoNaturalPersonMapper {
    @Mapping(target = "civilIdType.id", source = "personalIdType.code")
    @Mapping(target = "foreignIdentifierCountry", source = "foreignerIdentifierCountry")
    @Mapping(target = "foreignIdentifierType", source = "foreignerIdentifierKind")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "originCountry", source = "birthCountry")
    @Mapping(target = "originCity", source = "birthPlace")
    @Mapping(target = "originSettlement", source = "birthSettlement")
    @Mapping(target = "birthDate", source = "dateOfBirth")
    @Mapping(target = "citizenship", source = "citizenship")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "middleName", source = "middleName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "otherName", source = "otherName")
    @Mapping(target = "latinFirstName", source = "latinFirstName")
    @Mapping(target = "latinMiddleName", source = "latinMiddleName")
    @Mapping(target = "latinLastName", source = "latinLastName")
    @Mapping(target = "latinOtherName", source = "latinOtherName")
    @Mapping(target = "humanitarianStatus", source = "humanitarianStatus")
    @Mapping(target = "honorific", source = "title")

    public abstract PersonDTO toNaturalPersonDto(NaturalPersonDTO naturalPerson);

    @InheritConfiguration(name = "toNaturalPersonDto")
    public abstract void overridePersonDto(NaturalPersonDTO source, @MappingTarget PersonDTO target);

    @AfterMapping
    protected void afterToNaturalPersonDto(NaturalPersonDTO foNaturalPerson, @MappingTarget PersonDTO boPerson) {
        ReferenceDataDTO foreignIdentifierType = boPerson.getForeignIdentifierType();
        boolean isPersonalNacidId = Objects.nonNull(foreignIdentifierType) && ForeignIdType.NACID_GENERATED_NUMBER.code().equals(foreignIdentifierType.getId());
        boPerson.setCivilId(isPersonalNacidId ? foNaturalPerson.getPersonalNacidId() : foNaturalPerson.getPersonalId());

        ReferenceDataDTO naturalLegalType = new ReferenceDataDTO(ReferenceDataDomain.LEGAL_TYPE.domain(), LegalType.NATURAL_PERSON.code());
        boPerson.setLegalType(naturalLegalType);
        boPerson.getCivilIdType().setLegalType(naturalLegalType);
    }
}
