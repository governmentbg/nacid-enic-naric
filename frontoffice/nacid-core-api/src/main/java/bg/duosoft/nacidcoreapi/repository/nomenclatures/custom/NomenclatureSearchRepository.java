package bg.duosoft.nacidcoreapi.repository.nomenclatures.custom;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.base.NomenclatureEntityBase;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.base.BaseNomenclatureFilterDTO;

import java.io.Serializable;
import java.util.List;

public interface NomenclatureSearchRepository<ID extends Serializable, E extends NomenclatureEntityBase<ID>, F extends BaseNomenclatureFilterDTO<ID>> {

    List<E> searchRecords(F filter);

    int getRecordsCount(F filter);
}
