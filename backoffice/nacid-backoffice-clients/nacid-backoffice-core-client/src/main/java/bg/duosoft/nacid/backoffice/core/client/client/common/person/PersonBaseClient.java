package bg.duosoft.nacid.backoffice.core.client.client.common.person;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.PersonFormRequestDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PersonBaseClient {
    @GetMapping(value = "/{id}")
    PersonDTO selectById(@PathVariable("id") Integer id);

    @GetMapping
    public List<PersonDTO> searchByCivilId(@RequestParam("civilIdType") String civilIdType,
                                           @RequestParam("civilId") String civilId,
                                           @RequestParam(value = "foreignIdentifierType", required = false) String foreignIdentifierType,
                                           @RequestParam(value = "foreignIdentifierCountry", required = false) String foreignIdentifierCountry,
                                           @RequestParam(value = "active", required = false) Boolean active);
    @PutMapping
    public PersonDTO save(@RequestBody PersonFormRequestDTO requestData);
}
