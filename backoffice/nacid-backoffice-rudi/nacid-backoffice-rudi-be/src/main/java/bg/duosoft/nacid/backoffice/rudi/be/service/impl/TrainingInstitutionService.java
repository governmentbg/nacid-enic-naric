package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.referencedata.ReferenceDataClient;
import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.settlement.SettlementClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.AddressType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingInstitutionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.TrainingInstitutionFilterDTO;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.TrainingInstitutionMapper;
import bg.duosoft.nacid.backoffice.rudi.be.repository.TrainingInstitutionRepository;
import bg.duosoft.nacid.backoffice.rudi.be.validator.TrainingInstitutionValidator;
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
public class TrainingInstitutionService extends NomenclatureServiceBase<Integer, TrainingInstitutionDTO, TrainingInstitutionFilterDTO> {

    private final TrainingInstitutionRepository repository;
    private final TrainingInstitutionValidator validator;
    private final TrainingInstitutionMapper mapper;
    private final ReferenceDataClient referenceDataClient;
    private final SettlementClient settlementClient;

    @Override
    protected TrainingInstitutionRepository getNomenclaturesRepository() {
        return repository;
    }
    @Override
    protected TrainingInstitutionMapper getNomenclaturesMapper() {
        return mapper;
    }
    @Override
    protected BaseNomenclatureValidator<Integer, TrainingInstitutionDTO, TrainingInstitutionFilterDTO> getValidator() {
        return validator;
    }

    public List<TrainingInstitutionDTO> selectByUniversityIds(List<Integer> ids) {
       return mapper.toDtoList(repository.selectByUniversityIds(ids));
    }

    @Override
    protected void beforeCreateOrUpdate(TrainingInstitutionDTO dto) {
        dto.setCountry(dto.getAddress().getCountry());
        dto.getAddress().setAddressType(referenceDataClient.selectById(ReferenceDataDomain.ADDRESS_TYPE.domain(), AddressType.TRAINING_INSTITUTION.code()));
        if (dto.getAddress().getCountry().getId().equals(DefaultValue.BG_COUNTRY_CODE)) {
            dto.getAddress().setCity(null);
            dto.getAddress().setSettlement(settlementClient.selectById(dto.getAddress().getSettlement().getId()));
        } else {
            dto.getAddress().setSettlement(null);
        }
    }
}
