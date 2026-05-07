package bg.duosoft.nacid.backoffice.rudi.be.controller.v1;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CompetentInstitutionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingInstitutionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.CompetentInstitutionFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacid.backoffice.rudi.be.service.impl.CompetentInstitutionService;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import bg.duosoft.nacidbackofficeshareddata.controller.NomenclatureBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.COMPETENT_INSTITUTION)
@RequestMapping("/api/v1/competent-institution")
public class CompetentInstitutionController extends NomenclatureBaseController<Integer, CompetentInstitutionDTO, CompetentInstitutionFilterDTO> {
    private final CompetentInstitutionService service;

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

    @Override
    public String getAccessRole() {
        return null;
    }

    @Override
    protected CompetentInstitutionService getService() {
        return service;
    }

    @GetMapping(value = "/by-country/{id}")
    @ApiOperation(value = "Select records by country id")
    public List<CompetentInstitutionDTO> selectByUniversity(@PathVariable String id) {
        if (Objects.nonNull(id)) {
            return service.selectByCountry(id);
        }

        return null;
    }

    @GetMapping(value = "/by-country/ids")
    @ApiOperation(value = "Select competent institutions by country ids")
    public List<CompetentInstitutionDTO> selectByCountryIds(@RequestParam("ids") List<String> ids) {
        return service.selectByCountryIds(ids);
    }

}
