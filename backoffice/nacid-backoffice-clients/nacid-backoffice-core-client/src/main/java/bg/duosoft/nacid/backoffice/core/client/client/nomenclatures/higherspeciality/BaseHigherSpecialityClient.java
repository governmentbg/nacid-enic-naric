package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.higherspeciality;

import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.BaseNomenclaturesClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.SecondarySpecialityDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BaseHigherSpecialityClient extends BaseNomenclaturesClient<Integer, SecondarySpecialityDTO> {

    @GetMapping
    List<String> getHigherSpecialities(@RequestParam String name, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize);

}
