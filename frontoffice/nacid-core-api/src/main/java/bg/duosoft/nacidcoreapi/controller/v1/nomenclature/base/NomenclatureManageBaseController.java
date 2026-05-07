package bg.duosoft.nacidcoreapi.controller.v1.nomenclature.base;

import bg.duosoft.nacidfrontofficedto.nomenclature.base.NomenclatureBase;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.base.BaseNomenclatureFilterDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

public abstract class NomenclatureManageBaseController<ID extends Serializable, D extends NomenclatureBase<ID>, F extends BaseNomenclatureFilterDTO<ID>> extends NomenclatureSearchBaseController<ID, D, F> {

    protected abstract String getEditRole();

    @PostMapping
    @ApiOperation(value = "Insert nomenclature value")
    public D create(@RequestBody D dto) {
        checkPermissions(getEditRole());
        return getService().save(dto);
    }

    @PutMapping
    @ApiOperation(value = "Update nomenclature value")
    public D update(@RequestBody D dto) {
        checkPermissions(getEditRole());
        return getService().update(dto);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete nomenclature value")
    void delete(@PathVariable("id") ID id) {
        checkPermissions(getEditRole());
        getService().delete(id);
    }

    @DeleteMapping(value = "/delete-all")
    @ApiOperation(value = "Delete all nomenclature values")
    void deleteAll() {
        checkPermissions(getEditRole());
        getService().deleteAll();
    }
}
