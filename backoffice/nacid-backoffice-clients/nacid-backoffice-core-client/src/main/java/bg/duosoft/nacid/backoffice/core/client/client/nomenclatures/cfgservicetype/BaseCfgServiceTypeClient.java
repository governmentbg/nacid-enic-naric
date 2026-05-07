package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.cfgservicetype;

import bg.duosoft.nacid.backoffice.core.client.client.BaseCrudClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.DateDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgServiceTypeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

public interface BaseCfgServiceTypeClient extends BaseCrudClient<Integer, CfgServiceTypeDTO> {

    @GetMapping(value = "/end-date/{serviceTypeId}/{appType}/{appSubType}")
    DateDTO calculateEndDate(@PathVariable("serviceTypeId") String serviceTypeId, @PathVariable("appType") String appType, @PathVariable("appSubType") String appSubType, @RequestParam String date);

}
