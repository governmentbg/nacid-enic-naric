package bg.duosoft.nacid.backoffice.rudi.be.controller.v1;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingInstitutionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.TrainingInstitutionFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacid.backoffice.rudi.be.service.impl.TrainingInstitutionService;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
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
@Api(tags = Tags.TRAINING_INSTITUTION)
@RequestMapping("/api/v1/training-institution")
public class TrainingInstitutionController extends NomenclatureBaseController<Integer, TrainingInstitutionDTO, TrainingInstitutionFilterDTO> {

    private final TrainingInstitutionService service;

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

    @Override
    public String getAccessRole() {
        return null;
    }

    @Override
    protected TrainingInstitutionService getService() {
        return service;
    }

    @GetMapping(value = "/university/ids")
    @ApiOperation(value = "Select training institutions by university ids")
    public List<TrainingInstitutionDTO> selectByCountryIds(@RequestParam("ids") List<Integer> ids) {
        return service.selectByUniversityIds(ids);
    }
}
