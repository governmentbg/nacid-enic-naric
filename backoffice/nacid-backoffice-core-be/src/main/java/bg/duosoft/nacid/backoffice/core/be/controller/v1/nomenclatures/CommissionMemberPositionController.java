package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacidbackofficeshareddata.controller.NomenclatureBaseController;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.CommissionMemberPositionService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CommissionMemberPositionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CommissionMemberPositionFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_COMMISSION_MEMBER_POSITION)
@RequestMapping("/api/v1/commission-member-position")
public class CommissionMemberPositionController extends NomenclatureBaseController<String, CommissionMemberPositionDTO, CommissionMemberPositionFilterDTO> {
    private final CommissionMemberPositionService service;

    @Override
    protected CommissionMemberPositionService getService() {
        return service;
    }

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

}
