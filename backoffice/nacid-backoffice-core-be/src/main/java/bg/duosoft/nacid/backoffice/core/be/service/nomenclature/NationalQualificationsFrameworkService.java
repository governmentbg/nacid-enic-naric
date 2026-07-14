package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.NationalQualificationsFrameworkRepository;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures.NationalQualificationsFrameworkValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.NationalQualificationFrameworkDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.NationalQualificationFrameworkFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.NationalQualificationsFrameworkMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class NationalQualificationsFrameworkService extends NomenclatureServiceBase<Integer, NationalQualificationFrameworkDTO, NationalQualificationFrameworkFilterDTO> {

    private final NationalQualificationsFrameworkMapper mapper;
    private final NationalQualificationsFrameworkValidator validator;
    private final NationalQualificationsFrameworkRepository repository;

    @Override
    protected NationalQualificationsFrameworkRepository getNomenclaturesRepository() {
        return repository;
    }

    @Override
    protected NationalQualificationsFrameworkMapper getNomenclaturesMapper() {
        return mapper;
    }

    @Override
    protected NationalQualificationsFrameworkValidator getValidator() {
        return validator;
    }

    public List<NationalQualificationFrameworkDTO> selectByCountryAndEduLevelId(String countryCode) {
        return mapper.toDtoList(repository.selectByCountryCode(countryCode));
    }
}
