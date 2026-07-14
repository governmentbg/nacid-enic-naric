package bg.duosoft.nacid.backoffice.core.be.controller.v1.common;

import bg.duosoft.nacid.backoffice.core.be.service.common.PersonManagementService;
import bg.duosoft.nacid.backoffice.core.be.service.common.PersonService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.Page;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonSearchDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonSearchResultDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.autocomplete.LegalPersonAutocompleteDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.filter.LegalEntityFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.PersonFormRequestDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import bg.duosoft.nacidshareddata.util.civil_id.CivilIdUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.PERSONS)
@RequestMapping("/api/v1/persons")
public class PersonController extends BaseAccessController {

    private final static int MAX_PERSON_SEARCH_RESULTS = 50;

    private final PersonService personService;
    private final PersonManagementService personManagementService;

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

    @Override
    public String getAccessRole() {
        return null;
    }

    @GetMapping({"/{id}"})
    @ApiOperation("Select person by id")
    public PersonDTO getById(@PathVariable("id") Integer id) {
        PersonDTO result = personService.selectById(id);
        if (Objects.isNull(result)) {
            throw new ResourceNotFoundException();
        }
        return result;
    }

    @GetMapping
    @ApiOperation("Select persons by civil id")
    public List<PersonDTO> searchByCivilId(@RequestParam("civilIdType") String civilIdType,
                                           @RequestParam("civilId") String civilId,
                                           @RequestParam(value = "foreignIdentifierType", required = false) String foreignIdentifierType,
                                           @RequestParam(value = "foreignIdentifierCountry", required = false) String foreignIdentifierCountry,
                                           @RequestParam(value = "active", required = false) Boolean active) {
        return personService.selectByCivilId(civilIdType, civilId, foreignIdentifierType, foreignIdentifierCountry, active);
    }

    @PostMapping({"/search-for-applications-use"})
    @ApiOperation("Search for person records")
    public List<PersonSearchResultDTO> searchForApplicationsUse(@RequestBody PersonSearchDTO searchCriteria) {
        List<PersonSearchResultDTO> result = personService.searchForApplicationsUse(searchCriteria, MAX_PERSON_SEARCH_RESULTS);
        if (CollectionUtils.isEmpty(result)) {
            throw new ResourceNotFoundException();
        }
        return result;
    }

    @GetMapping({"/search"})
    @ApiOperation("Filter persons")
    public Page<PersonDTO> searchData(PersonFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        List<PersonDTO> results = personService.searchRecords(filter, true);
        return new Page<>(personService.getRecordsCount(filter), results, filter.getPageSize());
    }

    @DeleteMapping({"/{id}"})
    @ApiOperation("Delete person")
    public void delete(@PathVariable("id") Integer id) {
        if (Objects.nonNull(id)) {
            personService.deletePerson(id);
        }
    }

    @PostMapping({"/legal-applicants/autocomplete"})
    @ApiOperation("Select legal applicants")
    public List<LegalPersonAutocompleteDTO> searchLegalApplicants(@RequestBody LegalEntityFilterDTO filter) {
        List<PersonDTO> legalPersonList = personService.searchLegalApplicants(filter);
        return legalPersonList.stream().map(person -> LegalPersonAutocompleteDTO.legalPersonBuilder().id(person.getId()).eik(person.getCivilId()).name(person.getLegalName()).isActive(person.getIsActive()).build()).collect(Collectors.toList());
    }

    @PostMapping({"/legal-entities/autocomplete"})
    @ApiOperation("Select legal applicants")
    public List<LegalPersonAutocompleteDTO> searchLegalEntities(@RequestBody LegalEntityFilterDTO filter) {
        List<PersonDTO> legalPersonList = personService.searchLegalEntities(filter);
        return legalPersonList.stream().map(person -> LegalPersonAutocompleteDTO.legalPersonBuilder().id(person.getId()).eik(person.getCivilId()).name(person.getLegalName()).isActive(person.getIsActive()).build()).collect(Collectors.toList());
    }

    @PostMapping("/representative-companies/autocomplete")
    @ApiOperation("Select representative companies")
    public List<LegalPersonAutocompleteDTO> selectRepresentativeCompanies(@RequestBody LegalEntityFilterDTO filter) {
        List<PersonDTO> representativeCompanies = personService.searchRepresentativeCompanies(filter);
        return representativeCompanies.stream().map(person -> LegalPersonAutocompleteDTO.legalPersonBuilder().id(person.getId()).eik(person.getCivilId()).name(person.getLegalName()).isActive(person.getIsActive()).build()).collect(Collectors.toList());
    }

    @PutMapping
    @ApiOperation(value = "Save person")
    public PersonDTO save(@RequestBody PersonFormRequestDTO requestData) {
        PersonDTO save = personManagementService.processPersonSaving(requestData, requestData.getCreateNewPersonVersionFlag());
        if (Objects.isNull(save)) {
            throw new ResourceNotFoundException();
        }
        return save;
    }


    @GetMapping("/birth-date/extraction/{civilId}")
    @ApiOperation("Extract birth date from civil id")
    public LocalDate extractBirthDateFromCivilId(@PathVariable String civilId) {
        try {
            return CivilIdUtils.getBirthDate(civilId);
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }

}
