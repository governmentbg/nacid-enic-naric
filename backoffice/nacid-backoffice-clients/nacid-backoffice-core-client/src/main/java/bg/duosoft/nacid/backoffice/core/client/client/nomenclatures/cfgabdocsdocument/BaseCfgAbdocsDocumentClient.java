package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.cfgabdocsdocument;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgAbdocsDocumentDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface BaseCfgAbdocsDocumentClient {

    @GetMapping("/{id}")
    CfgAbdocsDocumentDTO getById(@PathVariable("id") String id);

    @GetMapping
    CfgAbdocsDocumentDTO getByAppTypeAndAppSubType(@RequestParam("appType") String appType, @RequestParam("appSubType") String appSubType);

    @GetMapping("/by-type")
    CfgAbdocsDocumentDTO getByAppType(@RequestParam("appType") String appType);
}
