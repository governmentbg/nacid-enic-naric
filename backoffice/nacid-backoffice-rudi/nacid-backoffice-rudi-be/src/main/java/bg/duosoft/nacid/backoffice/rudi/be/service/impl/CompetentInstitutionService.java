package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.referencedata.ReferenceDataClient;
import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.settlement.SettlementClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.AddressType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CompetentInstitutionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.CompetentInstitutionFilterDTO;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.CompetentInstitutionEntity;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.CompetentInstitutionMapper;
import bg.duosoft.nacid.backoffice.rudi.be.repository.CompetentInstitutionRepository;
import bg.duosoft.nacid.backoffice.rudi.be.validator.CompetentInstitutionValidator;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacidbackofficeshareddata.validator.BaseNomenclatureValidator;
import bg.duosoft.nacidshareddata.util.DefaultValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class CompetentInstitutionService extends NomenclatureServiceBase<Integer, CompetentInstitutionDTO, CompetentInstitutionFilterDTO> {
    private final CompetentInstitutionRepository repository;
    private final CompetentInstitutionMapper mapper;
    private final CompetentInstitutionValidator validator;
    private final SettlementClient settlementClient;
    private final ReferenceDataClient referenceDataClient;

    public List<CompetentInstitutionDTO> selectByCountry(String id) {
        List<CompetentInstitutionEntity> competentInstitutions = repository.selectByCountry(id);
        return mapper.toDtoList(competentInstitutions);
    }

    public List<CompetentInstitutionDTO> selectByCountryIds(List<String> ids) {
        return mapper.toDtoList(repository.selectByCountries(ids));
    }

    @Override
    protected void beforeCreateOrUpdate(CompetentInstitutionDTO dto) {
        dto.setCountry(dto.getAddress().getCountry());
        dto.getAddress().setAddressType(referenceDataClient.selectById(ReferenceDataDomain.ADDRESS_TYPE.domain(), AddressType.COMPETENT_INSTITUTION.code()));
        if (dto.getAddress().getCountry().getId().equals(DefaultValue.BG_COUNTRY_CODE)) {
            dto.getAddress().setCity("");
            dto.getAddress().setSettlement(settlementClient.selectById(dto.getAddress().getSettlement().getId()));
        } else {
            dto.getAddress().setSettlement(null);
        }
    }
    @Override
    protected CompetentInstitutionRepository getNomenclaturesRepository() {
        return repository;
    }
    @Override
    protected CompetentInstitutionMapper getNomenclaturesMapper() {
        return mapper;
    }
    @Override
    protected BaseNomenclatureValidator<Integer, CompetentInstitutionDTO, CompetentInstitutionFilterDTO> getValidator() {
        return validator;
    }
}
