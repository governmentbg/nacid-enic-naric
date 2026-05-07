package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.cfgeduleveltoapptype;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgEduLevelToAppTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgSarAppStatusDTO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BaseCfgEduLevelToAppTypeClient {
    @GetMapping("/all")
    public List<CfgEduLevelToAppTypeDTO> getAll();
    @PostMapping
    CfgEduLevelToAppTypeDTO insert(@RequestBody CfgEduLevelToAppTypeDTO dto);

}
