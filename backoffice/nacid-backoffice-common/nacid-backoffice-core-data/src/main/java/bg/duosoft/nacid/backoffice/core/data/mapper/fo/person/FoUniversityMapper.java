package bg.duosoft.nacid.backoffice.core.data.mapper.fo.person;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.LegalNatureType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.LegalType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.person.UniversityDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class FoUniversityMapper {
    @Mapping(target = "legalName", source = "universityName")
    @Mapping(target = "civilId", source = "universityIdentifier")
    @Mapping(target = "civilIdType.id", source = "companyIdentifierType.code")
    @Mapping(target = "originCountry", source = "universityCountry")
    @Mapping(target = "originSettlement", source = "universitySettlement")
    public abstract PersonDTO toUniversityPersonDto(UniversityDTO naturalPerson);

    @InheritConfiguration(name = "toUniversityPersonDto")
    public abstract void overridePersonDto(UniversityDTO source, @MappingTarget PersonDTO target);

    @AfterMapping
    protected void afterToUniversityPerson(UniversityDTO source, @MappingTarget PersonDTO target) {
        target.setLegalNatureType(new ReferenceDataDTO(ReferenceDataDomain.LEGAL_NATURE_TYPE.domain(), LegalNatureType.UNIVERSITY.code()));

        ReferenceDataDTO legalType = new ReferenceDataDTO(ReferenceDataDomain.LEGAL_TYPE.domain(), LegalType.LEGAL_ENTITY.code());
        target.setLegalType(legalType);
        target.getCivilIdType().setLegalType(legalType);
    }
}
