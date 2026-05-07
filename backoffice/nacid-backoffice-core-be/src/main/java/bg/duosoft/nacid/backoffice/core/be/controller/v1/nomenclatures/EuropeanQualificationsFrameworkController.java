package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacidbackofficeshareddata.controller.NomenclatureBaseController;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.EuropeanQualificationsFrameworkService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.EuropeanQualificationFrameworkDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.EuropeanQualificationFrameworkFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_EUROPEAN_QUALIFICATIONS_FRAMEWORK)
@RequestMapping("/api/v1/european-qualifications-framework")
public class EuropeanQualificationsFrameworkController extends NomenclatureBaseController<Integer, EuropeanQualificationFrameworkDTO, EuropeanQualificationFrameworkFilterDTO> {

    private final EuropeanQualificationsFrameworkService service;

    @Override
    protected EuropeanQualificationsFrameworkService getService() {
        return service;
    }

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

}
