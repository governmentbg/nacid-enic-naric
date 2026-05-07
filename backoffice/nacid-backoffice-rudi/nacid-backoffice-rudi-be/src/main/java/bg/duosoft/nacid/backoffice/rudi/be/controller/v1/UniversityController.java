package bg.duosoft.nacid.backoffice.rudi.be.controller.v1;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.Page;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.autocomplete.UniversityAutocompleteDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.FacultyDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.UniversityDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.UniversityFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacid.backoffice.rudi.be.service.UniversityFacultyService;
import bg.duosoft.nacid.backoffice.rudi.be.service.UniversityService;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import bg.duosoft.nacidshared.web.controller.CrudController;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.UNIVERSITY)
@RequestMapping("/api/v1/universities")
public class UniversityController extends CrudController<Integer, UniversityDTO> {

    private final UniversityService service;
    private final UniversityFacultyService facultyService;

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

    @Override
    public String getAccessRole() {
        return SecurityRole.BO_NOMENCLATURES_ACCESS;
    }

    @Override
    protected UniversityService getService() {
        return service;
    }

    @GetMapping({"/with-faculty-usage/{id}"})
    @ApiOperation("Select single value with faculty usage data")
    public UniversityDTO getByIdWithFacultyData(@PathVariable("id") Integer id) {
        UniversityDTO result = service.selectByIdWithFacultyData(id);
        if (Objects.isNull(result)) {
            throw new ResourceNotFoundException();
        } else {
            return result;
        }
    }

    @GetMapping(value = "/search")
    @ApiOperation(value = "Filter records")
    @PreAuthorize("hasRole(T(bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole).BO_NOMENCLATURES_ACCESS)")
    public Page<UniversityDTO> searchData(UniversityFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        List<UniversityDTO> results = service.searchRecords(filter);
        return new Page<>(service.getRecordsCount(filter), results, filter.getPageSize());
    }

    @GetMapping(value = "/select-by-country")
    @ApiOperation(value = "Select records by country code")
    public List<UniversityDTO> autocompleteData(String countryCode) {
        if (StringUtils.hasText(countryCode))
            return service.selectByCountry(countryCode);
        else
            return null;
    }

    @GetMapping(value = "/faculties")
    @ApiOperation(value = "Get university faculty records")
    public List<FacultyDTO> autocompleteFaculty(Integer universityId) {
        if (Objects.nonNull(universityId))
            return service.selectUniversityFacultiesByUniversityId(universityId);
        else
            return null;
    }

    @PostMapping("/faculties/{universityId}")
    @ApiOperation("Create faculty")
    public FacultyDTO createFaculty(@RequestBody FacultyDTO dto, @PathVariable("universityId") Integer universityId) {
        return facultyService.create(dto, service.selectById(universityId));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/toggle-activation/{id}")
    @ApiOperation(value = "Toggle activation")
    public void toggleActivation(@PathVariable("id") Integer id) {
        service.toggleActivation(id);
    }

    @GetMapping(value = "/autocomplete")
    @ApiOperation(value = "Filter universities for autocomplete")
    public List<UniversityAutocompleteDTO> searchForAutocomplete(UniversityFilterDTO filter) {
        List<UniversityAutocompleteDTO> result = new ArrayList<>();
        Page<UniversityDTO> universityDTOPage = searchData(filter);
        if (Objects.nonNull(universityDTOPage) && !CollectionUtils.isEmpty(universityDTOPage.getContent())) {
            List<UniversityDTO> content = universityDTOPage.getContent();
            result = content.stream()
                    .map(s -> UniversityAutocompleteDTO.universityBuilder()
                            .id(s.getId())
                            .name(s.getBgName())
                            .nameEn(s.getOrgName())
                            .country(s.getCountry().getName())
                            .city(StringUtils.hasText(s.getAddress().getCity()) ?
                                    s.getAddress().getCity() :
                                    s.getAddress().getSettlement().getSimpleSettlementName())
                            .isActive(s.getIsActive())
                            .build()
                    ).collect(Collectors.toList());
        }
        return result;
    }

    @GetMapping(value = "/autocomplete-faculties")
    public List<FacultyDTO> searchFacultyForAutocomplete(@RequestParam Integer universityId, @RequestParam String name,
                                                         @RequestParam(required = false, defaultValue = "false") Boolean onlyActive,
                                                         @RequestParam(required = false) Integer page,
                                                         @RequestParam(required = false) Integer pageSize) {
        List<FacultyDTO> faculties = service.searchUniFacultiesByName(universityId, name, onlyActive, page, pageSize);
        return faculties;
    }

    @GetMapping(value = "/select-name-by-id/{id}")
    @ApiOperation(value = "Select record name by id")
    public String selectName(@PathVariable("id") Integer id) {
        if (Objects.nonNull(id)) {
            String name = service.selectNameById(id);
            if (!StringUtils.hasText(name)) {
                throw new ResourceNotFoundException();
            }
            return name;
        } else
            return null;
    }

}
