package bg.duosoft.nacid.backoffice.core.be.service.common.impl;

import bg.duosoft.nacid.backoffice.core.be.service.common.PersonManagementService;
import bg.duosoft.nacid.backoffice.core.be.service.common.PersonService;
import bg.duosoft.nacid.backoffice.core.be.validation.common.PersonValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersonManagementServiceImpl implements PersonManagementService {

    private final PersonService personService;
    private final PersonValidator validator;

    @Override
    public PersonDTO processPersonSaving(PersonDTO person, Boolean createNewVersion) {
        if (Objects.nonNull(createNewVersion) && createNewVersion) {
            List<PersonDTO> existingPersons = selectExistingPersons(person);
            if (!CollectionUtils.isEmpty(existingPersons)) {
                for (PersonDTO existingPerson : existingPersons) {
                    existingPerson.setIsActive(false);
                    personService.save(existingPerson, null);
                }
            }
        }
        return personService.save(person, validator);
    }

    private List<PersonDTO> selectExistingPersons(PersonDTO person) {
        List<PersonDTO> existingPersons = personService.selectByCivilId(
                CommonUtils.selectId(person.getCivilIdType()),
                person.getCivilId(),
                CommonUtils.selectId(person.getForeignIdentifierType()),
                CommonUtils.selectId(person.getForeignIdentifierCountry()),
                true);
        return existingPersons;
    }
}
