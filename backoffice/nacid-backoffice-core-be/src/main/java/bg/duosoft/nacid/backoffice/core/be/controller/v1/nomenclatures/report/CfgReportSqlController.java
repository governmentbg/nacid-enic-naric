package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures.report;

import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.CfgReportSqlService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.Page;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgReportFieldDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgReportSqlDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CfgReportSqlFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.SqlExpressionFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidshared.web.controller.CrudController;
import bg.duosoft.nacidshared.web.service.CrudServiceBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * User: ggeorgiev
 * Date: 04.11.2022
 * Time: 14:47
 */
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_REPORT_SQL)
@RequestMapping("/api/v1/cfg-report-sql")
public class CfgReportSqlController extends CrudController<String, CfgReportSqlDTO> {
    private final CfgReportSqlService service;

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

    @Override
    public String getAccessRole() {
        return null;
    }

    @Override
    protected CrudServiceBase<String, CfgReportSqlDTO> getService() {
        return service;
    }

    @GetMapping(value = "/search")
    @ApiOperation(value = "Filter nomenclature records")
    public Page<CfgReportSqlDTO> searchData(CfgReportSqlFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        List<CfgReportSqlDTO> reportSqlData = service.selectReportSqlData(filter);
        return new Page<>(service.selectReportSqlDataCount(filter), reportSqlData, filter.getPageSize());
    }

    @PostMapping("/get-parameters")
    public List<String> getParameters(@RequestBody SqlExpressionFilterDTO filter) {
        return service.getSqlParameterNames(filter.getSql());
    }
    @GetMapping("/get-sql-fields-by-report-sql-id")
    public List<CfgReportFieldDTO> readFieldsByReportSql(@RequestParam ("id") String id, @RequestParam("params") String[] requestParams) {
        Map<String, Object> params = getParams(requestParams);
        return service.getSqlColumnsByReportSqlId(id, params);
    }
    @PostMapping("/get-sql-fields-by-sql")
    public List<CfgReportFieldDTO> readFieldsBySql(@RequestBody SqlExpressionFilterDTO filter) {
        Map<String, Object> params = getParams(filter.getRequestParams());
        return service.getSqlColumnsBySql(filter.getSql(), params);
    }

    private Map<String, Object> getParams(String[] requestParams) {
        Map<String, Object> params;
        if (requestParams == null || requestParams.length == 0) {
            params = null;
        } else {
            params = new HashMap<>();
            for (String p : requestParams) {
                String[] parts = p.split("=");
                if (parts.length != 2) {
                    throw new RuntimeException("Unknown params length");
                }
                String key = parts[0];
                Object value = parts[1];
                if (NumberUtils.isCreatable(parts[1])) {
                    value = NumberUtils.createNumber(parts[1]);
                } else if ("applicationIds".equals(key) && !ObjectUtils.isEmpty(value)) {
                    value = Arrays.stream(parts[1].split(",")).map(r -> r.trim()).map(r -> NumberUtils.createNumber(r)).collect(Collectors.toList());
                }
                params.put(key, value);
            }
        }
        return params;
    }


}
