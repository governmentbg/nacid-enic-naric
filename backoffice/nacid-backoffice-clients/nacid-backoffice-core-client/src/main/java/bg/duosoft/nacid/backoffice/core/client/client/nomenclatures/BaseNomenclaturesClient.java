package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures;

import bg.duosoft.nacid.backoffice.core.client.client.BaseCrudClient;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ne extend-va BaseCrudClient, tyj kato tam ima drug getAll method - bez parametri!
 */
public interface BaseNomenclaturesClient<ID, D> extends BaseCrudClient<ID, D> {

    @Override
    default List<D> getAll() {
        return getAll(false);
    }

    @GetMapping
    List<D> getAll(@RequestParam("onlyActive") boolean onlyActive);

//    @DeleteMapping(value = "/delete-all")
//    void deleteAll();
}
