package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.secondaryprofqualification;

import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.BaseNomenclaturesClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.Page;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.SecondaryProfessionalQualificationDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface BaseSecondaryProfessionalQualificationClient extends BaseNomenclaturesClient<Integer, SecondaryProfessionalQualificationDTO> {

    @GetMapping("/search")
    Page<SecondaryProfessionalQualificationDTO> searchData(@RequestParam(required = false) String name,
                                            @RequestParam(required = false) Boolean isActive,
                                            @RequestParam(required = false) Integer page,
                                            @RequestParam(required = false) Integer pageSize);
}
