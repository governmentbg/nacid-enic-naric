package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.originaledulevel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 01.06.2023
 * Time: 18:16
 */
public interface BaseOriginalEduLevelClient {

    @GetMapping
    List<String> autocompleteOriginalEduLevels(@RequestParam String name, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize);

    @GetMapping("/translations")
    List<String> autocompleteOriginalEduLevelTranslations(@RequestParam String name, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize);
}
