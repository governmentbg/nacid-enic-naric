package bg.duosoft.nacidcoreapi.integration.naciddoc.client;

import bg.duosoft.nacidcoreapi.integration.naciddoc.domain.NacidDocumentDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.03.2024
 * Time: 18:40
 */
@FeignClient(name = "NacidDocumentClient", url = "${nacid.site-endpoint}")
public interface NacidDocumentClient {

    @GetMapping("/document.php")
    NacidDocumentDetails getNacidDocumentDetails(@RequestParam(name = "lang", defaultValue = "bg", required = false) String lang,
                                                        @RequestParam(name = "type", defaultValue = "block", required = false) String type,
                                                        @RequestParam(name = "id") String id);


    @GetMapping("/{pathVar}")
    byte[] getDocumentBytes(@PathVariable("pathVar") String path);

}
