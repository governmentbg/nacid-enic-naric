package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.ApplicationSubtypeService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationSubtypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ApplicationSubTypeFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidbackofficeshareddata.controller.NomenclatureBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_APPLICATION_SUBTYPE)
@RequestMapping("/api/v1/application-subtypes")
public class ApplicationSubtypeController extends NomenclatureBaseController<String, ApplicationSubtypeDTO, ApplicationSubTypeFilterDTO> {
    private final ApplicationSubtypeService applicationSubtypeService;

    @Override
    protected ApplicationSubtypeService getService() {
        return applicationSubtypeService;
    }

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

    @ApiOperation(value = "Select all nomenclature records")
    @GetMapping(path = "/bytype")
    public List<ApplicationSubtypeDTO> getAllByApplicationType(@RequestParam("applicationType") String applicationType, @RequestParam(value = "onlyActive", defaultValue = "false") boolean onlyActive) {
        return applicationSubtypeService.selectByApplicationType(applicationType, onlyActive);
    }

}
