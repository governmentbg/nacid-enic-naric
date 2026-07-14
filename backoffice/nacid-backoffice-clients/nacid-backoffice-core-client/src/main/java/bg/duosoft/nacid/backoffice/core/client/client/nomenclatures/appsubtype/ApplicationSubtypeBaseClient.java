package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.appsubtype;

import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.BaseNomenclaturesClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationSubtypeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.06.2022
 * Time: 17:18
 */
public interface ApplicationSubtypeBaseClient extends BaseNomenclaturesClient<String, ApplicationSubtypeDTO> {
    @GetMapping("/bytype")
    public List<ApplicationSubtypeDTO> selectAllByApplicationType(@RequestParam("applicationType") String applicationType, @RequestParam("onlyActive") boolean onlyActive);
}
