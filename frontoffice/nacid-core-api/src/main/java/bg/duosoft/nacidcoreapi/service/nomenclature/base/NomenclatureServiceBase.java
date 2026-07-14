package bg.duosoft.nacidcoreapi.service.nomenclature.base;

import bg.duosoft.nacidcoreapi.validation.nomenclatures.base.BaseNomenclatureValidator;
import bg.duosoft.nacidfrontofficedto.nomenclature.base.NomenclatureBase;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.base.BaseNomenclatureFilterDTO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.01.2023
 * Time: 13:24
 */
public interface NomenclatureServiceBase <ID extends Serializable, D extends NomenclatureBase<ID>, F extends BaseNomenclatureFilterDTO<ID>>{

    BaseNomenclatureValidator<ID, D, F> getValidator();
    List<D> searchRecords(F filter);
    int getRecordsCount(F filter);
    List<D> selectAll(boolean onlyActive);
    D selectById(ID objectId);
    D save(D dto);
    D update(D dto);
    void delete(ID objectId);
    void deleteAll();

}
