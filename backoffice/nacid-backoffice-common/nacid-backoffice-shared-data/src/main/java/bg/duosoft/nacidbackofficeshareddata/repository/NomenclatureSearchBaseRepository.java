package bg.duosoft.nacidbackofficeshareddata.repository;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.NomenclatureEntityBase;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.BaseNomenclatureFilterDTO;

import java.io.Serializable;
import java.util.List;

public interface NomenclatureSearchBaseRepository<ID extends Serializable, E extends NomenclatureEntityBase<ID>, F extends BaseNomenclatureFilterDTO<ID>> {
    List<E> searchRecords(F filter, boolean hasDistinct);
    List<E> searchRecords(F filter);
    int getRecordsCount(F filter, boolean hasDistinct);
    int getRecordsCount(F filter);

}