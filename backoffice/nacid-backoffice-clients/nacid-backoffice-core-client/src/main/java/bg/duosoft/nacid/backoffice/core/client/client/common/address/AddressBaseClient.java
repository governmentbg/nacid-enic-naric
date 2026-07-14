package bg.duosoft.nacid.backoffice.core.client.client.common.address;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.filter.AddressFilterDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AddressBaseClient {
    @GetMapping({"/{id}"})
    public AddressDTO selectById(@PathVariable("id") Integer id);
    
    @PostMapping({"/search-for-applications-use"})
    public List<AddressDTO> searchAddressesForApplicationsUse(@RequestBody AddressFilterDTO filter);

    @PostMapping({"/search"})
    public List<AddressDTO> searchRecords(@RequestBody AddressFilterDTO filter);

    @PutMapping
    public AddressDTO save(@RequestBody AddressDTO requestData);
}
