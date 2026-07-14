package bg.duosoft.nacidcoreapi.controller.v1.nomenclature.base;

import bg.duosoft.nacidcoreapi.service.nomenclature.base.NomenclatureServiceBase;
import bg.duosoft.nacidfrontofficedto.Page;
import bg.duosoft.nacidfrontofficedto.nomenclature.base.NomenclatureBase;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.base.BaseNomenclatureFilterDTO;
import bg.duosoft.nacidshareddata.exception.ForbiddenException;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public abstract class NomenclatureSearchBaseController<ID extends Serializable, D extends NomenclatureBase<ID>, F extends BaseNomenclatureFilterDTO<ID>> {

    @GetMapping
    @ApiOperation(value = "Select all nomenclature records")
    public List<D> getAll(@RequestParam(value = "onlyActive", defaultValue = "false") boolean onlyActive) {
        checkPermissions(getAccessRole());
        return getService().selectAll(onlyActive);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Select single nomenclature value")
    public D getById(@PathVariable("id") ID id) {
        checkPermissions(getAccessRole());
        D result = getService().selectById(id);
        if (Objects.isNull(result)) {
            throw new ResourceNotFoundException();
        }
        return result;
    }

    @GetMapping(value = "/search")
    @ApiOperation(value = "Filter nomenclatures")
    public Page<D> searchData(F filter) {
        checkPermissions(getAccessRole());
        filter.setPage(filter.getPage() + 1);
        List<D> results = getService().searchRecords(filter);
        return new Page<>(getService().getRecordsCount(filter), results, filter.getPageSize());
    }

    protected void checkPermissions(String role) {
        if (Objects.nonNull(role) && !SecurityUtils.hasRole(role)) {
            throw new ForbiddenException();
        }
    }

    //Services for getting nomenclatures are not secured by default, because they could be invoked in different forms.
    protected String getAccessRole() {
        return null;
    }

    protected abstract NomenclatureServiceBase<ID, D, F> getService();
}
