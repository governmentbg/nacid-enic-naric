package bg.duosoft.nacidshared.web.controller;

import bg.duosoft.nacidshared.web.service.CrudServiceBase;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * User: ggeorgiev
 * Date: 15.09.2022
 * Time: 14:01
 */
public abstract class CrudController<ID extends Serializable, D> extends BaseAccessController {
    protected abstract CrudServiceBase<ID, D> getService();
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Select single value")
    public D getById(@PathVariable("id") ID id) {
        D result = getService().selectById(id);
        if (Objects.isNull(result)) {
            throw new ResourceNotFoundException();
        }
        return result;
    }
    @PostMapping
    @ApiOperation(value = "Insert  value")
    public D create(@RequestBody D dto) {
        return getService().create(dto);
    }

    @PutMapping
    @ApiOperation(value = "Update value")
    public D update(@RequestBody D dto) {
        return getService().update(dto);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete value")
    public void delete(@PathVariable("id") ID id) {
        getService().delete(id);
    }

    @GetMapping
    @ApiOperation(value = "Select all records")
    public List<D> getAll() {
        return getService().selectAll();
    }
}
