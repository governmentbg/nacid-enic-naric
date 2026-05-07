package bg.duosoft.nacidservicesclient.client;

import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 18.01.2023
 * Time: 16:58
 */
public interface ServicesBaseApplicationClient<A extends CommonApplicationDTO> {

    @GetMapping("/{id}")
    A getApplication(@PathVariable Integer id);
}
