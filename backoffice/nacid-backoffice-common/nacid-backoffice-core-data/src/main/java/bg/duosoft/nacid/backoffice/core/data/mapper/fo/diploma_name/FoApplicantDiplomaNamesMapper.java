package bg.duosoft.nacid.backoffice.core.data.mapper.fo.diploma_name;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicantDiplomaNamesDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ForeignIdType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.LegalType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CivilIdTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.fo.common.FoCountryMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.fo.common.FoReferenceDataMapper;
import bg.duosoft.nacidfrontofficedto.person.NaturalPersonNamesDTO;
import bg.duosoft.nacidfrontofficedto.services.regprof.QualificationDocumentNamesDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@Mapper(componentModel = "spring")
public abstract class FoApplicantDiplomaNamesMapper {

    @Autowired
    private FoReferenceDataMapper referenceDataMapper;
    @Autowired
    private FoCountryMapper countryMapper;

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "middleName", source = "middleName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "otherName", source = "otherName")
    @Mapping(target = "latinFirstName", source = "latinFirstName")
    @Mapping(target = "latinMiddleName", source = "latinMiddleName")
    @Mapping(target = "latinLastName", source = "latinLastName")
    @Mapping(target = "latinOtherName", source = "latinOtherName")
    public abstract ApplicantDiplomaNamesDTO toApplicantDiplomaNames(NaturalPersonNamesDTO source);

    @AfterMapping
    protected void afterToApplicantDiplomaNames(NaturalPersonNamesDTO source, @MappingTarget ApplicantDiplomaNamesDTO target) {
        if (source instanceof QualificationDocumentNamesDTO qualificationDocumentNames) {
            target.setForeignIdentifierType(referenceDataMapper.toReferenceData(qualificationDocumentNames.getForeignerIdentifierKind()));
            target.setForeignIdentifierCountry(countryMapper.toCountry(qualificationDocumentNames.getForeignerIdentifierCountry()));

            String personalIdType = Objects.nonNull(qualificationDocumentNames.getPersonalIdType()) ? qualificationDocumentNames.getPersonalIdType().getCode() : null;
            target.setCivilIdType(Objects.nonNull(personalIdType) ? new CivilIdTypeDTO(personalIdType) : null);

            CivilIdTypeDTO civilIdType = target.getCivilIdType();
            if (Objects.nonNull(civilIdType)) {
                civilIdType.setLegalType(new ReferenceDataDTO(ReferenceDataDomain.LEGAL_TYPE.domain(), LegalType.NATURAL_PERSON.code()));
            }

            ReferenceDataDTO foreignIdentifierType = target.getForeignIdentifierType();
            boolean isPersonalNacidId = Objects.nonNull(foreignIdentifierType) && ForeignIdType.NACID_GENERATED_NUMBER.code().equals(foreignIdentifierType.getId());
            target.setCivilId(isPersonalNacidId ? qualificationDocumentNames.getPersonalNacidId() : qualificationDocumentNames.getPersonalId());
        }
    }
}
