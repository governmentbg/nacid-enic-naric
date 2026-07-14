package bg.duosoft.nacid.backoffice.libserv.client.client;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BaseCrudClient<ID, D> {

    @GetMapping
    List<D> getAll();

    @GetMapping(value = "/{id}")
    D selectById(@PathVariable("id") ID id);

    @PostMapping
    D create(@RequestBody D dto);

    @PutMapping
    D update(@RequestBody D dto);

    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable("id") ID id);

}
