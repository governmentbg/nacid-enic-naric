package bg.duosoft.nacid.backoffice.core.data.util.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.CivilIdType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ForeignIdType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CivilIdTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacidshareddata.util.DefaultValue;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class PersonDTODataManager {

    public static void setPredefinedData(PersonDTO person) {
        if (Objects.isNull(person)) {
            return;
        }

        ReferenceDataUtils.setDefaultDomain(person.getForeignIdentifierType(), ReferenceDataDomain.FOREIGN_IDENTIFIER_TYPE);
        ReferenceDataUtils.setDefaultDomain(person.getLegalType(), ReferenceDataDomain.LEGAL_TYPE);
        ReferenceDataUtils.setDefaultDomain(person.getLegalNatureType(), ReferenceDataDomain.LEGAL_NATURE_TYPE);
        ReferenceDataUtils.setDefaultDomain(person.getHumanitarianStatus(), ReferenceDataDomain.HUMANITARIAN_STATUS);

        setActiveFlag(person);
        setNaturalPersonData(person);
        setLegalEntityData(person);
        setForeignerIdentifierData(person);
        setOriginData(person);
        setHumanitarianStatusData(person);
    }

    private static void setActiveFlag(PersonDTO person) {
        Integer id = person.getId();
        if (Objects.isNull(id)) {
            person.setIsActive(true);
        }
    }

    private static void setNaturalPersonData(PersonDTO target) {
        if (PersonUtils.isNaturalPerson(target)) {
            target.setLegalName(null);
            target.setLegalNatureType(null);
        }
    }

    private static void setLegalEntityData(PersonDTO target) {
        if (PersonUtils.isLegalEntity(target)) {
            target.setFirstName(null);
            target.setMiddleName(null);
            target.setLastName(null);
            target.setBirthDate(null);
            target.setCitizenship(null);
            target.setHonorific(null);
            target.setCivilIdType(new CivilIdTypeDTO(CivilIdType.EIK.code()));// TODO Foreign companies ?
        }
    }

    private static void setOriginData(PersonDTO target) {
        CountryDTO originCountry = target.getOriginCountry();
        if (Objects.nonNull(originCountry)) {
            if (DefaultValue.BG_COUNTRY_CODE.equalsIgnoreCase(originCountry.getId())) {
                target.setOriginCity(null);
            } else {
                target.setOriginSettlement(null);
            }
        }
    }

    private static void setHumanitarianStatusData(PersonDTO target) {
        if (PersonUtils.isLegalEntity(target)) {
            target.setHumanitarianStatus(null);
            return;
        }

        String civilIdType = CommonUtils.selectId(target.getCivilIdType());
        if (StringUtils.hasText(civilIdType)) {
            CivilIdType civilIdTypeEnum = CivilIdType.selectByCode(civilIdType);
            if (civilIdTypeEnum != CivilIdType.EGN && civilIdTypeEnum != CivilIdType.LNCH) {
                target.setHumanitarianStatus(null);
            }
        }

        String humanitarianStatus = CommonUtils.selectId(target.getHumanitarianStatus());
        if (!StringUtils.hasText(humanitarianStatus)) {
            target.setHumanitarianStatus(null);
        }
    }

    private static void setForeignerIdentifierData(PersonDTO target) {
        CivilIdTypeDTO civilIdType = target.getCivilIdType();
        if (Objects.nonNull(civilIdType)) {
            CivilIdType civilIdTypeEnum = CivilIdType.selectByCode(civilIdType.getId());
            switch (civilIdTypeEnum) {
                case EGN, LNCH, EIK -> {
                    target.setForeignIdentifierCountry(null);
                    target.setForeignIdentifierType(null);
                }
                case FOREIGN_COUNTRY_ID -> {
                    String foreignIdTypeId = CommonUtils.selectId(target.getForeignIdentifierType());
                    if (StringUtils.hasText(foreignIdTypeId)) {
                        ForeignIdType foreignIdType = ForeignIdType.selectByCode(foreignIdTypeId);
                        if (foreignIdType == ForeignIdType.NACID_GENERATED_NUMBER) {
                            target.setForeignIdentifierCountry(new CountryDTO(DefaultValue.BG_COUNTRY_CODE));
                        }
                    }
                }
            }
        }
    }

}
