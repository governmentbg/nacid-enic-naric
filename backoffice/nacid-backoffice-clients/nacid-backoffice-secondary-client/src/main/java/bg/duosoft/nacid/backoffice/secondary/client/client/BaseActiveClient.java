package bg.duosoft.nacid.backoffice.secondary.client.client;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * ne extend-va BaseCrudClient, tyj kato tam ima drug getAll method - bez parametri!
 */
public interface BaseActiveClient<ID, D> extends BaseCrudClient<ID, D> {

    @Override
    default List<D> getAll() {
        throw new NotImplementedException("Not implemented. Use getAll(onlyActive) instead!");
    }

    @GetMapping
    List<D> getAll(@RequestParam("onlyActive") boolean onlyActive);

//    @DeleteMapping(value = "/delete-all")
//    void deleteAll();
}
