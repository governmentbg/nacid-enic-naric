package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.qualification;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 08.02.2023
 * Time: 16:30
 */
public interface BaseQualificationClient {

    @GetMapping
    List<String> selectQualifications(@RequestParam String name, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize);
}
