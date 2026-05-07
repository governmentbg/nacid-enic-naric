package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationsService;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.CommissionMemberPositionService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.Page;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CommissionMemberPositionDTO;
import bg.duosoft.nacidbackofficeshareddata.controller.NomenclatureBaseController;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.LegalReasonService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.LegalReasonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.LegalReasonFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_LEGAL_REASON)
@RequestMapping("/api/v1/legal-reason")
public class LegalReasonController extends NomenclatureBaseController<Integer, LegalReasonDTO, LegalReasonFilterDTO> {
    private final LegalReasonService legalReasonService;
    private final CommissionMemberPositionService commissionMemberPositionService;
    public final ApplicationsService applicationService;

    @Override
    protected LegalReasonService getService() {
        return legalReasonService;
    }

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

    @GetMapping(value = "/by-status/{code}")
    @ApiOperation(value = "Select records by status code")
    public List<LegalReasonDTO> selectByStatus(@PathVariable("code") String code, @RequestParam(value = "onlyActive", defaultValue = "false") boolean onlyActive) {
        return legalReasonService.selectByStatusCode(code, onlyActive);
    }

    @GetMapping(value = "/by-application-and-status/{application_id}/{status}")
    @ApiOperation(value = "Select records by application and status code")
    public List<LegalReasonDTO> selectByApplicationAndStatus(
            @PathVariable("status") String code,
            @RequestParam(value = "onlyActive", defaultValue = "false") boolean onlyActive,
            @PathVariable("application_id")Integer applicationId,
            @RequestParam(value = "selected_legal_reason_id", required = false) Integer selectedLegalReasonId) {
        Pair<String, String> typeSubType = applicationService.getAppTypeAndSubtypeById(applicationId);
        return legalReasonService.selectByStatusApplicationTypeSubtype(selectedLegalReasonId, code, typeSubType.getFirst(), typeSubType.getSecond(), onlyActive);
    }


    @GetMapping(value = "/by-member-position-code/{code}")
    @ApiOperation(value = "Select records by status code")
    public List<LegalReasonDTO> selectByMemberPositionCode(@PathVariable("code") String code, @RequestParam(value = "onlyActive", defaultValue = "false") boolean onlyActive) {
        CommissionMemberPositionDTO commissionMemberPositionDTO = commissionMemberPositionService.selectById(code);
        return legalReasonService.selectByStatusCode(commissionMemberPositionDTO.getApplicationStatus().getId(), onlyActive);
    }

    @GetMapping(value = "/search")
    @ApiOperation(value = "Filter nomenclatures")
    public Page<LegalReasonDTO> searchData(LegalReasonFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        List<LegalReasonDTO> results = getService().searchRecords(filter, true);
        return new Page<>(getService().getRecordsCount(filter, true), results, filter.getPageSize());
    }

}
