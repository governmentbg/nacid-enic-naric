package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.cfgrecognitioncategorytoapptype;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgRecognitionCategoryToAppTypeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BaseCfgRecognitionCategoryToAppTypeClient {
    @GetMapping("/all")
    public List<CfgRecognitionCategoryToAppTypeDTO> getAll();
    @PostMapping
    CfgRecognitionCategoryToAppTypeDTO insert(@RequestBody CfgRecognitionCategoryToAppTypeDTO dto);

}
