package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.ReferenceDataService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.Page;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.BaseNomenclatureDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDomainDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ReferenceDataFilterDTO;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_REFERENCE_DATA)
@RequestMapping("/api/v1/reference-data")
public class ReferenceDataController extends BaseAccessController {

    private final ReferenceDataService referenceDataService;

    @GetMapping
    @ApiOperation(value = "Select all nomenclature records")
    public List<ReferenceDataDTO> getAll(@RequestParam("domain") String domain, @RequestParam(value = "onlyActive", defaultValue = "false") boolean onlyActive) {
        return referenceDataService.selectAll(domain, onlyActive);
    }

    @GetMapping(value = "/search")
    @ApiOperation(value = "Filter nomenclature records")
    public Page<ReferenceDataDTO> searchData(ReferenceDataFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        List<ReferenceDataDTO> referenceData = referenceDataService.selectReferenceData(filter);
        return new Page<>(referenceDataService.selectReferenceDataCount(filter), referenceData, filter.getPageSize());
    }


    @GetMapping(value = "/{domain}/{id}")
    @ApiOperation(value = "Select single nomenclature value")
    public ReferenceDataDTO getById(@PathVariable("domain") String domain, @PathVariable("id") String id) {
        ReferenceDataDTO result = referenceDataService.selectById(domain, id);
        if (Objects.isNull(result)) {
            throw new ResourceNotFoundException();
        }

        return result;
    }

    @PostMapping
    @ApiOperation(value = "Insert nomenclature value")
    public ReferenceDataDTO create(@RequestBody ReferenceDataDTO dto) {
        return referenceDataService.create(dto);
    }

    @PutMapping
    @ApiOperation(value = "Update nomenclature value")
    public ReferenceDataDTO update(@RequestBody ReferenceDataDTO dto) {
        return referenceDataService.update(dto);
    }

    @GetMapping("/base-nomenclature")
    @ApiOperation(value = "Select all nomenclature records")
    public List<BaseNomenclatureDTO> getAllBaseNomenclature(@RequestParam("domain") String domain, @RequestParam(value = "onlyActive", defaultValue = "false") boolean onlyActive) {
        List<ReferenceDataDTO> referenceDataDTOS = referenceDataService.selectAll(domain, onlyActive);
        return referenceDataDTOS.stream()
                .map(status -> BaseNomenclatureDTO.newInstance(status.getId(), status.getName(), status.getName(), status.getIsActive(), status.getIndex()))
                .collect(Collectors.toList());
    }
    /*@DeleteMapping(value = "/all/{domain}")
    @ApiOperation(value = "Delete nomenclature values for domain")
    @PreAuthorize("hasRole(T(bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole).JOINED_NOMENCLATURES_EDIT)")
    public void deleteAll(@PathVariable("domain") String domain) {
        referenceDataService.deleteAll(domain);
    }*/

    @DeleteMapping(value = "/{domain}/{id}")
    @ApiOperation(value = "Delete nomenclature value")
    public void delete(@PathVariable("domain") String domain, @PathVariable("id") String id) {
        referenceDataService.delete(domain, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/toggle-activation/{domain}/{id}")
    @ApiOperation(value = "Toggle activation")
    public void toggleActivation(@PathVariable("domain") String domain, @PathVariable("id") String id) {
        referenceDataService.toggleActivation(domain, id);
    }

    @GetMapping(value = "/domains")
    @ApiOperation(value = "Select domains")
    public List<ReferenceDataDomainDTO> selectDomains() {
        return referenceDataService.selectDomains();
    }
    @PostMapping("/domains")
    @ApiOperation(value = "Insert domain")
    public ReferenceDataDomainDTO create(@RequestBody ReferenceDataDomainDTO dto) {
        return referenceDataService.create(dto);
    }

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

    @Override
    public String getAccessRole() {
        return null;
    }
}
