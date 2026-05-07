package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.graduationdocumenttype;

import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.BaseNomenclaturesClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgGraduationDocumentTypeConfigDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.GraduationDocumentTypeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BaseGraduationDocumentTypeClient extends BaseNomenclaturesClient<Integer, GraduationDocumentTypeDTO> {
    @GetMapping(value = "/all-by-country/{id}")
    public List<GraduationDocumentTypeDTO> getAllByCountry(@PathVariable("id") String countryCode);

}
