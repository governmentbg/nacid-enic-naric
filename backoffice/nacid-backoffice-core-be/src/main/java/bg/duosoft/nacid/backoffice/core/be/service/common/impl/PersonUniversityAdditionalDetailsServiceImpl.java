package bg.duosoft.nacid.backoffice.core.be.service.common.impl;

import bg.duosoft.logging.annotation.LogObjectChange;
import bg.duosoft.nacid.backoffice.core.be.repository.common.PersonUniversityAdditionalDetailsRepository;
import bg.duosoft.nacid.backoffice.core.be.service.common.PersonUniversityAdditionalDetailsService;
import bg.duosoft.nacid.backoffice.core.be.validation.common.PersonUniversityAdditionalDetailsValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.PersonUniversityAdditionalDetailsEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonUniversityAdditionalDetailsDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.PersonUniversityAdditionalDetailsMapper;
import bg.duosoft.nacidshareddata.exception.ValidationErrorException;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersonUniversityAdditionalDetailsServiceImpl implements PersonUniversityAdditionalDetailsService {

    private final PersonUniversityAdditionalDetailsMapper mapper;
    private final PersonUniversityAdditionalDetailsRepository repository;
    private final PersonUniversityAdditionalDetailsValidator validator;

    @Override
    public PersonUniversityAdditionalDetailsDTO selectById(Integer id) {
        if (Objects.isNull(id)) {
            return null;
        }

        PersonUniversityAdditionalDetailsEntity personUniversityAdditionalDetailsEntity = repository.findById(id).orElse(null);
        return mapper.toDto(personUniversityAdditionalDetailsEntity);
    }

    @Override
    @LogObjectChange(id = "#result.universityId", before = "#root.target.selectById(#personUniversityAdditionalDetailsDTO.universityId)", after = "#result", operation = "#personUniversityAdditionalDetailsDTO.universityId == null ? 'create' : 'update'")
    public PersonUniversityAdditionalDetailsDTO save(PersonUniversityAdditionalDetailsDTO personUniversityAdditionalDetailsDTO) {

        List<ValidationError> errors = validator.validate(personUniversityAdditionalDetailsDTO);
        if (!CollectionUtils.isEmpty(errors)) {
            throw new ValidationErrorException(errors);
        }

        PersonUniversityAdditionalDetailsEntity personUniversityAdditionalDetailsEntity = mapper.toEntity(personUniversityAdditionalDetailsDTO);
        return mapper.toDto(repository.save(personUniversityAdditionalDetailsEntity));
    }

    @Override
    @LogObjectChange(id = "#id", before = "#root.target.selectById(#id)", operation = "'delete'")
    public void delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    @Override
    public PersonUniversityAdditionalDetailsDTO process(PersonUniversityAdditionalDetailsDTO dto) {
        if (!StringUtils.hasText(dto.getLetterGreeting()) && !StringUtils.hasText(dto.getLetterRecipient())) {
            delete(dto.getUniversityId());
        } else {
            return save(dto);
        }
        return null;
    }
}
