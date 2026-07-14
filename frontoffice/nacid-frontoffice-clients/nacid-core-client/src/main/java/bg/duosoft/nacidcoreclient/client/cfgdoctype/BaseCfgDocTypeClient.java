package bg.duosoft.nacidcoreclient.client.cfgdoctype;

import bg.duosoft.nacidfrontofficedto.nomenclature.CfgDocTypeDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 26.01.2023
 * Time: 18:41
 */
public interface BaseCfgDocTypeClient {

    @GetMapping("/for-app-type-subtype")
    List<CfgDocTypeDTO> getAllByAppTypeAndSubtype(@RequestParam ApplicationType applicationType, @RequestParam ApplicationSubtype applicationSubtype);
}
