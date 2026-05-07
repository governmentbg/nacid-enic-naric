package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacidbackofficeshareddata.controller.NomenclatureBaseController;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.CivilIdTypeService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CivilIdTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CivilIdTypeFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_CIVIL_ID_TYPE)
@RequestMapping("/api/v1/civil-id-types")
public class CivilIdTypeController extends NomenclatureBaseController<String, CivilIdTypeDTO, CivilIdTypeFilterDTO> {
    private final CivilIdTypeService civilIdTypeService;

    @Override
    protected CivilIdTypeService getService() {
        return civilIdTypeService;
    }

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

    @GetMapping(path = "/bytype")
    @ApiOperation(value = "Select all nomenclature records by legal type")
    public List<CivilIdTypeDTO> getAllByLegalType(@RequestParam("legalType") String legalType, @RequestParam(value = "onlyActive", defaultValue = "false") boolean onlyActive) {
        return civilIdTypeService.selectByLegalType(legalType, onlyActive);
    }
}
