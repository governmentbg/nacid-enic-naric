package bg.duosoft.nacid.backoffice.core.be.controller.v1.common;

import bg.duosoft.nacid.backoffice.core.be.service.common.ChangeRecordLogService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.Page;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ChangeRecordLogDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ChangeRecordLogFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ChangeRecordLogSimpleDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.StringKeyNomenclatureBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Api(tags = Tags.CHANGE_RECORD_LOG)
@RequestMapping("/api/v1/change-record-log")
@RequiredArgsConstructor
@PreAuthorize("hasRole(T(bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole).HISTORY_ACCESS)")
public class ChangeRecordLogController {

    private final ChangeRecordLogService changeRecordLogService;

    @ApiOperation(value = "Select filtered change record logs by application name")
    @GetMapping("/application/{applicationName}")
    public List<ChangeRecordLogSimpleDTO> selectByApplicationName(@PathVariable String applicationName, @RequestParam Integer page, @RequestParam Integer pageSize) {
        return changeRecordLogService.selectByApplicationName(applicationName, page, pageSize);
    }

    @ApiOperation(value = "Select change record log by id")
    @GetMapping("/{id}")
    public ChangeRecordLogDTO selectById(@PathVariable Integer id) {
        return changeRecordLogService.selectById(id);
    }

    @GetMapping({"/search"})
    @ApiOperation("Filter change record logs")
    public Page<ChangeRecordLogSimpleDTO> searchData(ChangeRecordLogFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        List<ChangeRecordLogSimpleDTO> results = changeRecordLogService.searchRecords(filter);
        return new Page<>(changeRecordLogService.getRecordsCount(filter), results, filter.getPageSize());
    }

    @GetMapping({"/services-dictionary"})
    @ApiOperation("Get change record logs service dictionary")
    public List<StringKeyNomenclatureBase> getDictionary(@RequestParam String applicationName) {
        List<StringKeyNomenclatureBase> dictionary = changeRecordLogService.selectServiceDictionary(applicationName);
        return dictionary;
    }
}
