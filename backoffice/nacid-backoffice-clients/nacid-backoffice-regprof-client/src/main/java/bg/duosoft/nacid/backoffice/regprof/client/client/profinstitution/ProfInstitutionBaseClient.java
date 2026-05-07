package bg.duosoft.nacid.backoffice.regprof.client.client.profinstitution;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.autocomplete.base.BaseAutocompleteDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.ProfInstitutionDTO;
import bg.duosoft.nacid.backoffice.regprof.client.client.BaseCrudClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProfInstitutionBaseClient extends BaseCrudClient<Integer, ProfInstitutionDTO> {

    @GetMapping(value = "/autocomplete")
    List<BaseAutocompleteDTO<Integer>> selectProfInstitutions(@RequestParam String educationType,
                                                              @RequestParam String name,
                                                              @RequestParam(required = false) Boolean isActive,
                                                              @RequestParam(required = false) Integer page,
                                                              @RequestParam(required = false) Integer pageSize);

    @GetMapping(value = "/former-name-autocomplete")
    List<BaseAutocompleteDTO<Integer>> selectProfInstitutionFormerNames(@RequestParam Integer profInstitutionId,
                                                                        @RequestParam String formerName,
                                                                        @RequestParam(required = false) Boolean isActive,
                                                                        @RequestParam(required = false) Integer page,
                                                                        @RequestParam(required = false) Integer pageSize);
}
