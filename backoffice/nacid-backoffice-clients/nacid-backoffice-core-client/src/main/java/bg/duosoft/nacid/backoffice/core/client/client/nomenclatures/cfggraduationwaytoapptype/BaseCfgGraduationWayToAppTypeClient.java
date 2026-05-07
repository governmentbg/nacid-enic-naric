package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.cfggraduationwaytoapptype;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgGraduationWayToAppTypeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BaseCfgGraduationWayToAppTypeClient {

    @GetMapping("/all")
    public List<CfgGraduationWayToAppTypeDTO> getAll();

    @PostMapping
    CfgGraduationWayToAppTypeDTO insert(@RequestBody CfgGraduationWayToAppTypeDTO dto);

}
