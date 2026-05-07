package bg.duosoft.nacid.backoffice.regprof.client.client.professionname;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProfessionNameBaseClient {

    @GetMapping
    List<String> selectProfessionNames(@RequestParam String name, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize);
}
