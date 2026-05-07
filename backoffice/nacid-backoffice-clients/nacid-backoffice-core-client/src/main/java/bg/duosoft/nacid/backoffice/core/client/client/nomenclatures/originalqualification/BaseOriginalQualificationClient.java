package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.originalqualification;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 01.06.2023
 * Time: 18:16
 */
public interface BaseOriginalQualificationClient {

    @GetMapping
    List<String> selectOriginalQualifications(@RequestParam String name, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize);
}
