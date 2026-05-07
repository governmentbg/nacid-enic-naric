package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.referencedata;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDomainDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BaseReferenceDataClient {

    @GetMapping
    List<ReferenceDataDTO> selectAll(@RequestParam("domain") String domain, @RequestParam("onlyActive") boolean onlyActive);

    @GetMapping(value = "/{domain}/{id}")
    ReferenceDataDTO selectById(@PathVariable("domain") String domain, @PathVariable("id") String id);

    @PostMapping
    ReferenceDataDTO create(@RequestBody ReferenceDataDTO dto);

    @PutMapping
    void update(@RequestBody ReferenceDataDTO dto);

    @DeleteMapping(value = "/{domain}/{id}")
    void delete(@PathVariable("domain")String domain, @PathVariable("id") String id);

    @GetMapping("/domains")
    public List<ReferenceDataDomainDTO> selectDomains();

    @PostMapping("/domains")
    public ReferenceDataDomainDTO create(@RequestBody ReferenceDataDomainDTO dto);

    /*@DeleteMapping(value = "/all/{domain}")
    void deleteAll(@PathVariable("domain")String domain);*/
}
