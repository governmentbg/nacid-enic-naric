package bg.duosoft.nacid.backoffice.core.be.controller.v1.common;

import bg.duosoft.nacid.backoffice.core.be.service.common.ErrorLogAutoResolutionService;
import bg.duosoft.nacid.backoffice.core.be.service.common.ErrorLogService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.Page;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ErrorLogDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ErrorLogFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ErrorLogResolutionDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import bg.duosoft.nacidshareddata.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.ERROR_LOG)
@RequestMapping("/api/v1/error-logs")
public class ErrorLogController extends BaseAccessController {

    private final ErrorLogService errorLogService;
    private final ErrorLogAutoResolutionService errorLogAutoResolutionService;

    @GetMapping({"/{id}"})
    @ApiOperation("Select record by id")
    public ErrorLogDTO selectById(@PathVariable Integer id) {
        return ResponseUtils.notFoundCheck(errorLogService.selectById(id));
    }

    @PatchMapping({"/{id}/resolution"})
    @ApiOperation("Resolve error log record")
    public ErrorLogDTO resolve(@PathVariable Integer id, @RequestBody ErrorLogResolutionDTO resolutionDto) {
        return ResponseUtils.notFoundCheck(errorLogService.resolveErrorLog(id, resolutionDto));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping({"/{id}/resolution/auto"})
    @ApiOperation("Resolve error log record automatically")
    public void resolveAutomatically(@PathVariable Integer id) {
        errorLogAutoResolutionService.resolveAutomatically(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping({"/resolution/auto"})
    @ApiOperation("Resolve all error log records automatically")
    public void resolveAllAutomatically() {
        log.debug("[ERROR LOG RESOLVER] Starting automatically resolving process...");
        List<Integer> identifiers = errorLogService.selectUnresolvedIdentifiers();
        if (!CollectionUtils.isEmpty(identifiers)) {
            for (Integer id : identifiers) {
                try {
                    errorLogAutoResolutionService.resolveAutomatically(id);
                    log.debug("[ERROR LOG RESOLVER] Error log record with ID = {} has been automatically resolved !", id);
                } catch (Exception e) {
                    log.debug("[ERROR LOG RESOLVER] Error log record with ID = {} cannot be resolved automatically !", id);
                    log.error(e.getMessage(), e);
                }
            }
            log.debug("[ERROR LOG RESOLVER] Automatically resolving process has ended successfully !");
        } else {
            log.debug("[ERROR LOG RESOLVER] Unresolved identifiers list is empty! All records from the database have been already resolved!");
        }
    }

    @GetMapping({"/unresolved/count"})
    @ApiOperation("Select unresolved records count")
    public Integer selectUnresolvedCount() {
        Integer count = errorLogService.selectUnresolvedCount();
        if (Objects.isNull(count)) {
            return 0;
        }
        return count;
    }

    @GetMapping({"/search"})
    @ApiOperation("Filter records")
    public Page<ErrorLogDTO> searchData(ErrorLogFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        List<ErrorLogDTO> results = errorLogService.searchRecords(filter);
        return new Page<>(errorLogService.getRecordsCount(filter), results, filter.getPageSize());
    }

    @Override
    public String getEditRole() {
        return SecurityRole.ERROR_LOG_EDIT;
    }

    @Override
    public String getAccessRole() {
        return SecurityRole.ERROR_LOG_ACCESS;
    }

}
