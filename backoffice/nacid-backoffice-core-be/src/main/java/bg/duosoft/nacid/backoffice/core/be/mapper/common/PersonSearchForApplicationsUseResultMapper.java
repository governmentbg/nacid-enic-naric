package bg.duosoft.nacid.backoffice.core.be.mapper.common;

import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.CivilIdTypeService;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.CountryService;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.ReferenceDataService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonSearchResultDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.StringIdNameDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CivilIdTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class PersonSearchForApplicationsUseResultMapper {

    private final CountryService countryService;
    private final ReferenceDataService referenceDataService;
    private final CivilIdTypeService civilIdTypeService;

    public List<PersonSearchResultDTO> toDtoList(List<Object[]> resultList) {
        if (CollectionUtils.isEmpty(resultList))
            return null;

        return resultList.stream()
                .map(object -> {
                    String civilIdTypeCode = (String) object[5];
                    CivilIdTypeDTO civilIdType = Objects.isNull(civilIdTypeCode) ? null : civilIdTypeService.selectById(civilIdTypeCode);

                    String legalTypeCode = (String) object[7];
                    ReferenceDataDTO legalType = Objects.isNull(legalTypeCode) ? null : referenceDataService.selectById(ReferenceDataDomain.LEGAL_TYPE.name(), (String) object[7]);

                    String legalNatureTypeCode = (String) object[8];
                    ReferenceDataDTO legalNatureType = Objects.isNull(legalNatureTypeCode) ? null : referenceDataService.selectById(ReferenceDataDomain.LEGAL_NATURE_TYPE.name(), legalNatureTypeCode);

                    String originCountryCode = (String) object[9];
                    CountryDTO originCountry = Objects.isNull(originCountryCode) ? null : countryService.selectById(originCountryCode);

                    String citizenshipCode = (String) object[10];
                    CountryDTO citizenship = Objects.isNull(citizenshipCode) ? null : countryService.selectById(citizenshipCode);

                    Integer activeInt = (Integer) object[14];
                    return PersonSearchResultDTO.builder()
                            .id((Integer) object[0])
                            .firstName((String) object[1])
                            .middleName((String) object[2])
                            .lastName((String) object[3])
                            .civilId((String) object[4])
                            .civilIdType(Objects.isNull(civilIdType) ? null : new StringIdNameDTO(civilIdType.getId(), civilIdType.getName()))
                            .legalName((String) object[6])
                            .legalType(Objects.isNull(legalType) ? null : new StringIdNameDTO(legalType.getId(), legalType.getName()))
                            .legalNatureType(Objects.isNull(legalNatureType) ? null : new StringIdNameDTO(legalNatureType.getId(), legalNatureType.getName()))
                            .originCountry(Objects.isNull(originCountry) ? null : new StringIdNameDTO(originCountry.getId(), originCountry.getName()))
                            .citizenship(Objects.isNull(citizenship) ? null : new StringIdNameDTO(citizenship.getId(), citizenship.getName()))
                            .email((String) object[11])
                            .originSettlementName((String) object[12])
                            .originCity((String) object[13])
                            .active(Objects.nonNull(activeInt) && activeInt == 1)
                            .build();
                })
                .collect(Collectors.toList());

    }


}
