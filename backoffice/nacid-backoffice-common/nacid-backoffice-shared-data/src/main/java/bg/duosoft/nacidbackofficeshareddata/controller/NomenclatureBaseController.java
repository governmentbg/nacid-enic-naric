package bg.duosoft.nacidbackofficeshareddata.controller;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.Page;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.NomenclatureBase;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.BaseNomenclatureFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacidshared.web.controller.CrudController;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.NotImplementedException;

import java.io.Serializable;
import java.util.List;

public abstract class NomenclatureBaseController<ID extends Serializable, D extends NomenclatureBase<ID>, F extends BaseNomenclatureFilterDTO<ID>> extends CrudController<ID, D> {

    protected abstract NomenclatureServiceBase<ID, D, F> getService();

    //Services for getting nomenclatures are not secured by default, because they could be invoked in different forms.
    @Override
    public String getAccessRole() {
        return null;
    }

    @Override
    @GetMapping("/all/disabled")
    @ApiOperation(value = "Select all records", hidden = true)
    public List<D> getAll() {
        throw new NotImplementedException("Not implemented...");
    }

    @GetMapping
    @ApiOperation(value = "Select all nomenclature records")
    public List<D> getAll(@RequestParam(value = "onlyActive", defaultValue = "false") boolean onlyActive) {
        return getService().selectAll(onlyActive);
    }

    @GetMapping(value = "/search")
    @ApiOperation(value = "Filter nomenclatures")
    public Page<D> searchData(F filter) {
        filter.setPage(filter.getPage() + 1);
        List<D> results = getService().searchRecords(filter);
        return new Page<>(getService().getRecordsCount(filter), results, filter.getPageSize());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/toggle-activation/{id}")
    @ApiOperation(value = "Toggle activation")
    public void toggleActivation(@PathVariable("id") ID id) {
        getService().toggleActivation(id);
    }

}
