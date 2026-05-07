package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacidbackofficeshareddata.controller.NomenclatureBaseController;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.BolognaCycleService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.BolognaCycleDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.BolognaCycleFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_BOLOGNA_CYCLE)
@RequestMapping("/api/v1/bologna-cycle")
public class BolognaCycleController extends NomenclatureBaseController<Integer, BolognaCycleDTO, BolognaCycleFilterDTO> {

    private final BolognaCycleService service;

    @Override
    protected BolognaCycleService getService() {
        return service;
    }

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

}
