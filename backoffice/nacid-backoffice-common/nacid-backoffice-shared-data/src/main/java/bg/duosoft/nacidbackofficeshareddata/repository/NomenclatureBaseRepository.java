package bg.duosoft.nacidbackofficeshareddata.repository;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.NomenclatureEntityBase;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.BaseNomenclatureFilterDTO;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * User: ggeorgiev
 * Date: 31.08.2022
 * Time: 14:27
 */
@NoRepositoryBean
public interface NomenclatureBaseRepository<ID extends Serializable, E extends NomenclatureEntityBase<ID>, F extends BaseNomenclatureFilterDTO<ID>> extends BaseRepository<E, ID>, NomenclatureSearchBaseRepository<ID, E, F> {
    public List<NomenclatureEntityBase<ID>> getAllByActive(Integer active);
}
