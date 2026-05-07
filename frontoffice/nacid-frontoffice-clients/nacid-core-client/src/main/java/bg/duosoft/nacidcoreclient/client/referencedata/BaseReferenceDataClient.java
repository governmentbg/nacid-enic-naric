package bg.duosoft.nacidcoreclient.client.referencedata;

import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BaseReferenceDataClient {


    @GetMapping
    List<ReferenceDataDTO> selectAll(@RequestParam("domain") String domain, @RequestParam(value = "onlyActive", defaultValue = "true") boolean onlyActive);

    @GetMapping(value = "/{domain}/{id}")
    ReferenceDataDTO getById(@PathVariable("domain") String domain, @PathVariable("id") String id);

}
