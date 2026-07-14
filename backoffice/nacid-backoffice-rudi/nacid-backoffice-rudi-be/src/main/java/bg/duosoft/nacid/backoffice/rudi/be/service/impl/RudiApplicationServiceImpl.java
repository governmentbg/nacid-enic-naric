package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.logging.annotation.LogObjectChange;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.RudiApplicationEntity;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.RudiApplicationMapper;
import bg.duosoft.nacid.backoffice.rudi.be.repository.RudiApplicationRepository;
import bg.duosoft.nacid.backoffice.rudi.be.service.RudiApplicationService;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.RudiApplicationValidator;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.ValidationScope;
import bg.duosoft.nacidshareddata.exception.ValidationErrorException;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RudiApplicationServiceImpl implements RudiApplicationService {

    private final RudiApplicationRepository repository;
    private final RudiApplicationMapper mapper;
    private final RudiApplicationValidator validator;

    @Override
    public Integer selectEfilingIdById(Integer id) {
        if (Objects.isNull(id)) {
            return null;
        }

        return repository.selectEfilingIdById(id);
    }

    @Override
    public RudiApplicationDTO selectById(Integer id) {
        if (Objects.isNull(id)) {
            return null;
        }

        RudiApplicationEntity entity = repository.findById(id).orElse(null);
        return mapper.toDto(entity);
    }

    @Override
    public boolean existsByIdAndType(Integer id, ApplicationSubType subType) {
        if (Objects.isNull(id)) {
            return false;
        }

        Integer result = repository.checkExists(id, subType.appSubType());
        if (Objects.isNull(result)) {
            return false;
        }

        return result == 1;
    }

    @Override
    public boolean isFoAppAlreadyAccepted(Integer efilingId) {
        if (Objects.isNull(efilingId)) {
            throw new RuntimeException("Efiling id is not present !");
        }

        Integer result = repository.isAlreadyAccepted(efilingId);
        if (Objects.isNull(result)) {
            return false;
        }

        return result == 1;
    }

    @Override
    @LogObjectChange(id = "#result.application.id", before = "#root.target.selectById(#dto.application.id)", after = "#result", operation = "#dto.application.id == null ? 'create' : 'update'")
    public RudiApplicationDTO save(RudiApplicationDTO dto, ValidationScope validationScope) {
        return save(dto, validationScope, true);
    }

    @Override
    @LogObjectChange(id = "#result.application.id", before = "#root.target.selectById(#dto.application.id)", after = "#result", condition = "#isLoggable", operation = "#dto.application.id == null ? 'create' : 'update'")
    public RudiApplicationDTO save(RudiApplicationDTO dto, ValidationScope validationScope, boolean isLoggable) {
        List<ValidationError> errors = validator.validate(dto, validationScope);
        if (!CollectionUtils.isEmpty(errors)) {
            throw new ValidationErrorException(errors);
        }

        RudiApplicationEntity rudiApplicationEntity = mapper.toEntity(dto);
        RudiApplicationEntity save = repository.save(rudiApplicationEntity);
        return mapper.toDto(save);
    }

    @Override
    public List<RudiApplicationDTO> selectAppsWithSimilarDiplomasById(Integer applicationId, Integer diplomaYear, String countryName, String eduLevel, String originalEduLevel, String civilId, String ownerFirstName, String ownerLastName, LocalDate birthDate, String birthCountry, String diplomaOwnerEan) {
        return mapper.toDtoList(repository.getAppsWithCommonDiplomasById(applicationId, diplomaYear, countryName, eduLevel, originalEduLevel, civilId, ownerFirstName, ownerLastName, birthDate != null ? birthDate.toString() : null, birthCountry, diplomaOwnerEan));
    }
}
