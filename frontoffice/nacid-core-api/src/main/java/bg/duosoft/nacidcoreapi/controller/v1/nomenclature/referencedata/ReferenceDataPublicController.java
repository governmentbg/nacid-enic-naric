package bg.duosoft.nacidcoreapi.controller.v1.nomenclature.referencedata;

import bg.duosoft.nacidcoreapi.service.nomenclature.ReferenceDataService;
import bg.duosoft.nacidcoreapi.util.swagger.Tags;
import bg.duosoft.nacidfrontofficedto.Page;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.ReferenceDataFilterDTO;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@Api(tags = Tags.NOM_REFERENCE_DATA)
@RequestMapping("/api/v1/reference-data")
@RequiredArgsConstructor
public class ReferenceDataPublicController {

    private final ReferenceDataService referenceDataService;

    @GetMapping(value = "/search")
    @ApiOperation(value = "Filter nomenclature records")
    public Page<ReferenceDataDTO> searchData(ReferenceDataFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        List<ReferenceDataDTO> referenceData = referenceDataService.selectFoReferenceData(filter);
        return new Page<>(referenceDataService.selectFoReferenceDataCount(filter), referenceData, filter.getPageSize());
    }

    @GetMapping
    @ApiOperation(value = "Select all nomenclature records")
    public List<ReferenceDataDTO> getAll(@RequestParam("domain") String domain, @RequestParam(value = "onlyActive", defaultValue = "false") boolean onlyActive) {
        return referenceDataService.selectAll(domain, onlyActive);
    }

    @GetMapping(value = "/{domain}/{id}")
    @ApiOperation(value = "Select single nomenclature value")
    public ReferenceDataDTO getById(@PathVariable("domain") String domain, @PathVariable("id") String id) {
        return referenceDataService.selectById(domain, id);
    }

}
