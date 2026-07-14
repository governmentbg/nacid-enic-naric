package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.secondaryspeciality;

import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.BaseNomenclaturesClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.Page;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.SecondarySpecialityDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface BaseSecondarySpecialityClient extends BaseNomenclaturesClient<Integer, SecondarySpecialityDTO> {

    @GetMapping("/search")
    Page<SecondarySpecialityDTO> searchData(@RequestParam(required = false) Integer qualificationCode,
                                            @RequestParam(required = false) String name,
                                            @RequestParam(required = false) Boolean isActive,
                                            @RequestParam(required = false) Integer page,
                                            @RequestParam(required = false) Integer pageSize);
}
