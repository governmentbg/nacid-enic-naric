package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationsService;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.ApplicationStatusService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.NormalStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.BaseNomenclatureDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_APPLICATION_STATUS)
@RequestMapping("/api/v1/application-status")
public class ApplicationStatusController extends BaseAccessController {

    public final ApplicationStatusService applicationStatusService;
    public final ApplicationsService applicationService;

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

    @Override
    public String getAccessRole() {
        return null;
    }

    @ApiOperation(value = "Select commission statuses")
    @GetMapping(path = "/commission")
    public List<ReferenceDataDTO> selectCommissionStatuses(@RequestParam(value = "onlyActive", defaultValue = "false") boolean onlyActive) {
        return applicationStatusService.selectCommissionStatuses(onlyActive);
    }

    @ApiOperation(value = "Select legal statuses")
    @GetMapping(path = "/legal")
    public List<ReferenceDataDTO> selectLegalStatuses(@RequestParam("applicationType") String applicationType,
                                                      @RequestParam(value = "applicationSubType", required = false) String applicationSubType, @RequestParam(value = "onlyActive", defaultValue = "false") boolean onlyActive) {
        return applicationStatusService.selectLegalStatuses(applicationType, applicationSubType, onlyActive);
    }

    @ApiOperation(value = "Select all by application type")
    @GetMapping(path = "/by-type/{applicationType}")
    public List<ReferenceDataDTO> selectByType(@PathVariable("applicationType") String applicationType, @RequestParam(value = "onlyActive", defaultValue = "false") boolean onlyActive) {
        return applicationStatusService.selectByApplicationType(applicationType, onlyActive);
    }

    @ApiOperation(value = "Select legal statuses")
    @GetMapping(path = "/by-types")
    public List<BaseNomenclatureDTO> selectAllByApplicationTypeAndSubType(@RequestParam("applicationType") String applicationType, @RequestParam("applicationSubType") String applicationSubType, @RequestParam(value = "onlyActive", defaultValue = "false") boolean onlyActive) {
        List<ReferenceDataDTO> referenceDataDTOS = applicationStatusService.selectByApplicationTypeAndSubType(applicationType, applicationSubType, onlyActive);
        return referenceDataDTOS.stream()
                .map(status -> BaseNomenclatureDTO.newInstance(status.getId(), status.getName(), status.getName(), status.getIsActive(), status.getIndex()))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Select normal statuses by application")
    @GetMapping(path = "/by-application/{id}/normal")
    public List<NormalStatusDTO> selectNormalStatusesByApplication(@PathVariable("id") Integer applicationId, @RequestParam(value = "onlyActive", defaultValue = "true") boolean onlyActive) {
        return applicationStatusService.selectNormalStatuses(applicationId, onlyActive);
    }

}
