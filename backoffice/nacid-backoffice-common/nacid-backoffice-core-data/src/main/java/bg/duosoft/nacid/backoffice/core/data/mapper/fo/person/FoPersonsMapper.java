package bg.duosoft.nacid.backoffice.core.data.mapper.fo.person;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.CivilIdType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.LegalType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CivilIdTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.person.NaturalPersonDTO;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.RudiApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.ServicesApplicantDTO;
import bg.duosoft.nacidshareddata.util.DefaultValue;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Mapper(componentModel = "spring")
public abstract class FoPersonsMapper {
    @Autowired
    private FoApplicantPersonMapper applicantPersonMapper;
    @Autowired
    private FoNaturalPersonMapper naturalPersonMapper;

    @BeanMapping(ignoreByDefault = true)
    public abstract void overrideApplicationPersons(CommonApplicantDetailsDTO source, @MappingTarget ApplicationDTO target);

    @AfterMapping
    protected void afterPersonsOverride(CommonApplicantDetailsDTO source, @MappingTarget ApplicationDTO target) {
        ServicesApplicantDTO applicant = source.getApplicant();
        if (Objects.nonNull(applicant)) {
            target.setApplicant(applicantPersonMapper.toApplicantPerson(source));
        }

        NaturalPersonDTO representative = source.getRepresentative();
        if (Objects.nonNull(representative)) {
            target.setRepresentative(naturalPersonMapper.toNaturalPersonDto(representative));
        }

        fillRepresentativeCompany(source, target);
    }

    private void fillRepresentativeCompany(CommonApplicantDetailsDTO source, ApplicationDTO target) {
        if (source instanceof RudiApplicantDetailsDTO rudiApplicantDetails) {
            if (StringUtils.hasText(rudiApplicantDetails.getRepresentativeCompanyIdentifier())) {
                PersonDTO representativeCompany = new PersonDTO();
                representativeCompany.setOriginCountry(new CountryDTO(DefaultValue.BG_COUNTRY_CODE));
                representativeCompany.setCivilId(rudiApplicantDetails.getRepresentativeCompanyIdentifier());
                representativeCompany.setCivilIdType(new CivilIdTypeDTO(CivilIdType.EIK.code()));

                ReferenceDataDTO legalType = new ReferenceDataDTO(ReferenceDataDomain.LEGAL_TYPE.domain(), LegalType.LEGAL_ENTITY.code());
                representativeCompany.setLegalType(legalType);
                representativeCompany.getCivilIdType().setLegalType(legalType);

                target.setRepresentativeCompany(representativeCompany);
            }
        }
    }
}
