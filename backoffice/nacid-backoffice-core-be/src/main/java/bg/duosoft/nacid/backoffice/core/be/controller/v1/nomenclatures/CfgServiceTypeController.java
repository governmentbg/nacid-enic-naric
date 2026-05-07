package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationsService;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.CfgServiceTypeService;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.WorkCalendarHolidayService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.DateDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ExecutionDaysType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgServiceTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CfgServiceTypeFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.CommonUtils;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidshared.web.controller.CrudController;
import bg.duosoft.nacidshared.web.service.CrudServiceBase;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import bg.duosoft.nacidshareddata.util.date.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.util.Pair;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static bg.duosoft.nacidshareddata.util.ResponseUtils.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_SERVICE_TYPE_CONFIG)
@RequestMapping("/api/v1/cfg-service-types")
public class CfgServiceTypeController extends CrudController<Integer, CfgServiceTypeDTO> {
    private final CfgServiceTypeService cfgServiceTypeService;
    private final ApplicationsService applicationsService;
    private final WorkCalendarHolidayService workCalendarHolidayService;

    @Override
    protected CrudServiceBase<Integer, CfgServiceTypeDTO> getService() {
        return cfgServiceTypeService;
    }

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

    @Override
    public String getAccessRole() {
        return null;
    }

    @GetMapping("/appType/{appType}/appSubType/{appSubType}/service-types")
    @ApiOperation(value = "Select all service type configs by app type and app subtype")
    public List<CfgServiceTypeDTO> selectIdentifiersByTypeAndSubtype(@PathVariable("appType") String appType, @PathVariable("appSubType") String appSubType) {
        List<CfgServiceTypeDTO> cfgResult = cfgServiceTypeService.selectByApplicationTypeAndSubType(appType, appSubType);
        if (CollectionUtils.isEmpty(cfgResult)) {
            throw new ResourceNotFoundException();
        }

        List<CfgServiceTypeDTO> serviceTypes = cfgResult.stream()
                .filter(s -> Objects.nonNull(s.getServiceType()))

                .toList();

        if (CollectionUtils.isEmpty(serviceTypes)) {
            throw new ResourceNotFoundException();
        }

        return serviceTypes;
    }


    @GetMapping("/appType/{appType}/service-types")
    @ApiOperation(value = "Select all service type configs by app type ")
    public List<CfgServiceTypeDTO> selectIdentifiersByType(@PathVariable("appType") String appType) {
        List<CfgServiceTypeDTO> cfgResult = cfgServiceTypeService.selectByApplicationTypeAndSubType(appType, null);
        if (CollectionUtils.isEmpty(cfgResult)) {
            throw new ResourceNotFoundException();
        }

        List<CfgServiceTypeDTO> serviceTypes = cfgResult.stream()
                .filter(s -> Objects.nonNull(s.getServiceType()))

                .toList();

        if (CollectionUtils.isEmpty(serviceTypes)) {
            throw new ResourceNotFoundException();
        }

        return serviceTypes;
    }

    @GetMapping(value = "/search")
    @ApiOperation(value = "Filter nomenclature records")
    public Page<CfgServiceTypeDTO> searchData(CfgServiceTypeFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        List<CfgServiceTypeDTO> data = cfgServiceTypeService.selectServiceTypeData(filter);
        return new Page<>(cfgServiceTypeService.selectCountServiceTypeData(filter), data, filter.getPageSize());
    }

    @GetMapping(value = "/end-date/{applicationId}/{serviceTypeId}")
    @ApiOperation(value = "Calculate end date")
    public DateDTO calculateEndDate(@PathVariable("applicationId") Integer applicationId, @PathVariable("serviceTypeId") String serviceTypeId) {
        LocalDateTime dateCreated = notFoundCheck(applicationsService.getDateCreated(applicationId));
        Pair<String, String> pair = notFoundCheck(applicationsService.getAppTypeAndSubtypeById(applicationId));
        return calculateEndDate(serviceTypeId, dateCreated, pair.getFirst(), pair.getSecond());
    }

    @GetMapping(value = "/end-date/{serviceTypeId}/{appType}/{appSubType}")
    @ApiOperation(value = "Calculate end date")
    public DateDTO calculateEndDate(@PathVariable("serviceTypeId") String serviceTypeId,
                                    @PathVariable("appType") String appType,
                                    @PathVariable("appSubType") String appSubType,
                                    @RequestParam String date) {

        LocalDate convertedDate = DateUtils.convertToLocalDate(date);
        return calculateEndDate(serviceTypeId, convertedDate.atStartOfDay(), appType, appSubType);
    }

    private DateDTO calculateEndDate(String serviceTypeId, LocalDateTime date, String appType, String appSubType) {
        List<CfgServiceTypeDTO> allConfigs = notFoundCheck(cfgServiceTypeService.selectByApplicationTypeAndSubType(appType, appSubType));
        CfgServiceTypeDTO selectedConfig = notFoundCheck(allConfigs.stream()
                .filter(s -> s.getServiceType().getId().equals(serviceTypeId))
                .findFirst()
                .orElse(null));

        Integer executionDays = notFoundCheck(selectedConfig.getExecutionDays());
        ExecutionDaysType type = ExecutionDaysType.selectByCode(CommonUtils.selectId(notFoundCheck(selectedConfig.getExecutionDaysType())));
        switch (type) {
            case WORKING_DAY -> {
                return new DateDTO(workCalendarHolidayService.calculateWorkingDaysPeriod(date.toLocalDate(), executionDays));
            }
            default -> {
                return new DateDTO(date.plusDays(executionDays).toLocalDate());
            }
        }
    }
}
