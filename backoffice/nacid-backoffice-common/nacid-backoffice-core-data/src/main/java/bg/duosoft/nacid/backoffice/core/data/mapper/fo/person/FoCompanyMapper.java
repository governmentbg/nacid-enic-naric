package bg.duosoft.nacid.backoffice.core.data.mapper.fo.person;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.LegalNatureType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.LegalType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.person.CompanyDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class FoCompanyMapper {

    @Mapping(target = "legalName", source = "companyName")
    @Mapping(target = "civilId", source = "companyIdentifier")
    @Mapping(target = "civilIdType.id", source = "companyIdentifierType.code")
    @Mapping(target = "originCountry", source = "companyCountry")
    @Mapping(target = "originCity", source = "companyCity")
    @Mapping(target = "originSettlement", source = "companySettlement")
    public abstract PersonDTO toCompanyPersonDto(CompanyDTO source);

    @InheritConfiguration(name = "toCompanyPersonDto")
    public abstract void overridePersonDto(CompanyDTO source, @MappingTarget PersonDTO target);

    @AfterMapping
    protected void afterToCompanyPerson(CompanyDTO source, @MappingTarget PersonDTO target) {
        target.setLegalNatureType(new ReferenceDataDTO(ReferenceDataDomain.LEGAL_NATURE_TYPE.domain(), LegalNatureType.COMPANY.code()));

        ReferenceDataDTO legalType = new ReferenceDataDTO(ReferenceDataDomain.LEGAL_TYPE.domain(), LegalType.LEGAL_ENTITY.code());
        target.setLegalType(legalType);
        target.getCivilIdType().setLegalType(legalType);
    }
}
