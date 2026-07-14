package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.EuropeanQualificationsFrameworkRepository;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.EuropeanQualificationsFrameworkValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.EuropeanQualificationFrameworkDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.EuropeanQualificationFrameworkFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.EuropeanQualificationsFrameworkMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EuropeanQualificationsFrameworkService extends NomenclatureServiceBase<Integer, EuropeanQualificationFrameworkDTO, EuropeanQualificationFrameworkFilterDTO> {

    private final EuropeanQualificationsFrameworkMapper mapper;
    private final EuropeanQualificationsFrameworkValidator validator;
    private final EuropeanQualificationsFrameworkRepository repository;

    @Override
    protected EuropeanQualificationsFrameworkRepository getNomenclaturesRepository() {
        return repository;
    }

    @Override
    protected EuropeanQualificationsFrameworkMapper getNomenclaturesMapper() {
        return mapper;
    }

    @Override
    protected EuropeanQualificationsFrameworkValidator getValidator() {
        return validator;
    }
}
