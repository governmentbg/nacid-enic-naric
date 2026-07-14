package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.civilidtype;

import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.BaseNomenclaturesClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CivilIdTypeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.06.2022
 * Time: 17:18
 */
public interface CivilIdTypeBaseClient extends BaseNomenclaturesClient<String, CivilIdTypeDTO> {
    @GetMapping("/bytype")
    public List<CivilIdTypeDTO> selectAllByLegalType(@RequestParam("legalType") String legalType, @RequestParam("onlyActive") boolean onlyActive);
}
