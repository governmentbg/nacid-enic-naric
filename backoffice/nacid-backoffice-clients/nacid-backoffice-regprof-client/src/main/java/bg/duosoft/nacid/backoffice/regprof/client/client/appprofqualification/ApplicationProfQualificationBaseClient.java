package bg.duosoft.nacid.backoffice.regprof.client.client.appprofqualification;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 17.05.2023
 * Time: 17:27
 */
public interface ApplicationProfQualificationBaseClient {

    @GetMapping
    List<String> selectApplicationProfQualifications(@RequestParam String name, @RequestParam Integer page, @RequestParam Integer pageSize);
}
