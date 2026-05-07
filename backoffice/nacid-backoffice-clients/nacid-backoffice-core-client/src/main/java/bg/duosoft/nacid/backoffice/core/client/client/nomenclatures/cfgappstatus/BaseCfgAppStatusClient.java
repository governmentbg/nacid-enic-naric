package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.cfgappstatus;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgAppStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgSarAppStatusDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BaseCfgAppStatusClient {

    @PostMapping
    CfgAppStatusDTO insert(@RequestBody CfgAppStatusDTO dto);

    @GetMapping("/sar")
    List<CfgSarAppStatusDTO> getAllSarStatusConfigs();

    @GetMapping("/sar/by-status/{statusCode}")
    List<CfgSarAppStatusDTO> getSarConfigsByStatus(@PathVariable("statusCode") String statusCode);

    @PostMapping("/sar")
    CfgSarAppStatusDTO insert(@RequestBody CfgSarAppStatusDTO dto);

    @GetMapping("/get-by-type-subtype")
    List<CfgAppStatusDTO> getByApplicationTypeAndSubType(@RequestParam String applicationType,
                                                                   @RequestParam String applicationSubType,
                                                                   @RequestParam(required = false, defaultValue = "true") boolean onlyActive);

    @DeleteMapping(value = "/delete-all")
    void deleteAll();
}
