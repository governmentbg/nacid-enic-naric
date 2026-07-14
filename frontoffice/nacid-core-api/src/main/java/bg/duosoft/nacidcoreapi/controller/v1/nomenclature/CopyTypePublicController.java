package bg.duosoft.nacidcoreapi.controller.v1.nomenclature;

import bg.duosoft.nacidcoreapi.service.nomenclature.CopyTypeService;
import bg.duosoft.nacidcoreapi.util.swagger.Tags;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 10.09.2025
 * Time: 15:03
 */
@RestController
@Api(tags = Tags.NOM_REFERENCE_DATA)
@RequestMapping("/api/v1/copy-type")
@RequiredArgsConstructor
public class CopyTypePublicController {
    private final CopyTypeService copyTypeService;
    @GetMapping
    @ApiOperation(value = "Select all copy type records by application type/subtype")
    public List<ReferenceDataDTO> getAll(@RequestParam ApplicationType applicationType, @RequestParam ApplicationSubtype applicationSubtype, @RequestParam(value = "onlyActive", defaultValue = "false") boolean onlyActive) {
        return copyTypeService.getAll(applicationType, applicationSubtype, onlyActive);
    }
}
