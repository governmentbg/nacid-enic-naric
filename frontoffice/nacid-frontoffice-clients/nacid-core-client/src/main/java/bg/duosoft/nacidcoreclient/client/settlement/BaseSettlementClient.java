package bg.duosoft.nacidcoreclient.client.settlement;

import bg.duosoft.nacidfrontofficedto.nomenclature.SettlementDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BaseSettlementClient {

    @GetMapping
    List<SettlementDTO> getAll(@RequestParam(value = "onlyActive", defaultValue = "false") boolean onlyActive);

    @GetMapping(value = "/{id}")
    SettlementDTO selectById(@RequestParam("id") String id);

}
