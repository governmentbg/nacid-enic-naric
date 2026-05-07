package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacidbackofficeshareddata.controller.NomenclatureBaseController;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.SecondaryProfessionGroupService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.SecondaryProfessionGroupDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.SecondaryProfessionGroupFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_SECONDARY_PROFESSION_GROUP)
@RequestMapping("/api/v1/secondary-profession-groups")
public class SecondaryProfessionGroupController extends NomenclatureBaseController<Integer, SecondaryProfessionGroupDTO, SecondaryProfessionGroupFilterDTO> {

    private final SecondaryProfessionGroupService service;

    @Override
    protected NomenclatureServiceBase<Integer, SecondaryProfessionGroupDTO, SecondaryProfessionGroupFilterDTO> getService() {
        return service;
    }

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }
}
