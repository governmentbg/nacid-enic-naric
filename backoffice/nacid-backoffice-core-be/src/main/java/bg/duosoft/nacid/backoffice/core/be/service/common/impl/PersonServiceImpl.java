package bg.duosoft.nacid.backoffice.core.be.service.common.impl;

import bg.duosoft.logging.annotation.LogObjectChange;
import bg.duosoft.nacid.backoffice.core.be.mapper.common.PersonSearchForApplicationsUseResultMapper;
import bg.duosoft.nacid.backoffice.core.be.repository.common.PersonRepository;
import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationsService;
import bg.duosoft.nacid.backoffice.core.be.service.common.PersonService;
import bg.duosoft.nacid.backoffice.core.be.service.common.PersonUniversityAdditionalDetailsService;
import bg.duosoft.nacid.backoffice.core.be.validation.common.PersonValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.PersonEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonSearchDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonSearchResultDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.filter.LegalEntityFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.PersonMapper;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import bg.duosoft.nacidshareddata.exception.ValidationErrorException;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonMapper mapper;
    private final PersonRepository repository;
    private final PersonSearchForApplicationsUseResultMapper searchForApplicationsUseMapper;
    private final PersonUniversityAdditionalDetailsService personUniversityAdditionalDetailsService;

    private final ApplicationsService applicationsService;

    @Override
    public PersonDTO selectById(Integer id) {
        if (Objects.isNull(id)) {
            return null;
        }

        PersonEntity personEntity = repository.findById(id).orElse(null);
        return mapper.toDto(personEntity);
    }

    @Override
    public List<PersonDTO> selectByCivilId(String civilIdType, String civilId, String foreignIdentifierType, String foreignIdentifierCountry, Boolean isActive) {
        if (!StringUtils.hasText(civilId)) {
            return null;
        }

        List<PersonEntity> personEntities = repository.findByCivilIdAndCivilIdTypeAndIsActive(civilIdType, civilId, foreignIdentifierType, foreignIdentifierCountry, isActive == null ? null : (isActive ? 1 : 0));
        if (CollectionUtils.isEmpty(personEntities)) {
            return null;
        }

        return mapper.toDtoList(personEntities);
    }

    @Override
    public List<PersonSearchResultDTO> searchForApplicationsUse(PersonSearchDTO searchCriteria, int maxResults) {
        List<Object[]> result = repository.searchForApplicationsUse(searchCriteria, maxResults);
        return searchForApplicationsUseMapper.toDtoList(result);
    }

    @Override
    public List<PersonDTO> searchLegalApplicants(LegalEntityFilterDTO filter) {
        return mapper.toDtoList(repository.searchLegalApplicants(filter));
    }

    @Override
    public List<PersonDTO> searchLegalEntities(LegalEntityFilterDTO filter) {
        return mapper.toDtoList(repository.searchLegalEntities(filter));
    }

    @Override
    public List<PersonDTO> searchRepresentativeCompanies(LegalEntityFilterDTO filter) {
        return mapper.toDtoList(repository.searchRepresentativeCompanies(filter));
    }

    @Override
    @LogObjectChange(id = "#result.id", before = "#root.target.selectById(#personDTO.id)", after = "#result", operation = "#personDTO.id == null ? 'create' : 'update'")
    public PersonDTO save(PersonDTO personDTO, PersonValidator validator) {
        mapper.overrideDtoData(personDTO);

        if (Objects.nonNull(validator)) {
            List<ValidationError> errors = validator.validate(personDTO);
            if (!CollectionUtils.isEmpty(errors)) {
                throw new ValidationErrorException(errors);
            }
        }

        PersonEntity personEntity = mapper.toEntity(personDTO);
        return mapper.toDto(repository.save(personEntity));
    }

    @Override
    public List<PersonDTO> searchRecords(PersonFilterDTO filter, boolean withAppsCount) {
        List<PersonDTO> result = mapper.toDtoList(repository.searchRecords(filter));
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }

        if (withAppsCount) {
            List<Integer> identifiers = result.stream().map(PersonDTO::getId).filter(Objects::nonNull).toList();
            List<PersonRepository.AppsPerPersonResult> applicationsCountForPersons = repository.getApplicationsCountForPersons(identifiers);
            if (!CollectionUtils.isEmpty(applicationsCountForPersons)) {
                Map<Integer, Integer> map = applicationsCountForPersons.stream().collect(Collectors.toMap(PersonRepository.AppsPerPersonResult::getPersonId, PersonRepository.AppsPerPersonResult::getConnectedAppsCount));
                for (PersonDTO personDTO : result) {
                    Integer count = map.get(personDTO.getId());
                    personDTO.setConnectedApplicationsCount(Objects.isNull(count) ? 0 : count);
                }
            }

        }

        return result;
    }

    @Override
    public int getRecordsCount(PersonFilterDTO filter) {
        return repository.getRecordsCount(filter);
    }

    @Override
    @Transactional
    public void deletePerson(Integer personId) {
        Optional<PersonEntity> person = repository.findById(personId);
        if (person.isPresent()) {
            Integer connectedApps = applicationsService.getApplicationsCountByPersonId(personId);
            if (connectedApps == 0) {
                personUniversityAdditionalDetailsService.delete(personId);
                repository.delete(person.get());
            } else {
                throw new RuntimeException("Person connected po apps!");
            }
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
