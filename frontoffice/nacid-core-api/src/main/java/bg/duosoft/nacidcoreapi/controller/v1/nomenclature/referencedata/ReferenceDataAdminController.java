package bg.duosoft.nacidcoreapi.controller.v1.nomenclature.referencedata;

import bg.duosoft.nacidcoreapi.service.nomenclature.ReferenceDataService;
import bg.duosoft.nacidcoreapi.util.swagger.Tags;
import bg.duosoft.nacidfrontofficedto.Page;
import bg.duosoft.nacidfrontofficedto.nomenclature.BaseNomenclatureDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDomainDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.ReferenceDataFilterDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@Api(tags = Tags.NOM_REFERENCE_DATA)
@PreAuthorize("hasRole(T(bg.duosoft.nacidcoredata.util.security.SecurityRole).JOINED_NOMENCLATURES_ACCESS)")
@RequestMapping("/api/v1/reference-data")
@RequiredArgsConstructor
public class ReferenceDataAdminController {

    private final ReferenceDataService referenceDataService;

    @GetMapping(value = "/domains")
    @ApiOperation(value = "Select domains")
    public List<BaseNomenclatureDTO> selectDomains() {
        List<ReferenceDataDomainDTO> domains = referenceDataService.getFoReferenceDataDomains();
        return domains.stream().map(domain -> BaseNomenclatureDTO.newInstance(domain.getDomain(), domain.getName(), domain.getName(), true)).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasRole(T(bg.duosoft.nacidcoredata.util.security.SecurityRole).JOINED_NOMENCLATURES_EDIT)")
    @ApiOperation(value = "Insert nomenclature value")
    public ResponseEntity create(@RequestBody ReferenceDataDTO dto) {
        referenceDataService.save(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @PreAuthorize("hasRole(T(bg.duosoft.nacidcoredata.util.security.SecurityRole).JOINED_NOMENCLATURES_EDIT)")
    @ApiOperation(value = "Update nomenclature value")
    public ResponseEntity update(@RequestBody ReferenceDataDTO dto) {
        referenceDataService.update(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/all/{domain}")
    @PreAuthorize("hasRole(T(bg.duosoft.nacidcoredata.util.security.SecurityRole).JOINED_NOMENCLATURES_EDIT)")
    @ApiOperation(value = "Delete nomenclature values for domain")
    void deleteAll(@PathVariable("domain") String domain) {
        referenceDataService.deleteAll(domain);
    }

    @DeleteMapping(value = "/{domain}/{id}")
    @PreAuthorize("hasRole(T(bg.duosoft.nacidcoredata.util.security.SecurityRole).JOINED_NOMENCLATURES_EDIT)")
    @ApiOperation(value = "Delete nomenclature value")
    void delete(@PathVariable("domain") String domain, @PathVariable("id") String id) {
        referenceDataService.delete(domain, id);
    }
}
