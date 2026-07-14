package bg.duosoft.nacidcoreapi.repository.nomenclatures.base;

import bg.duosoft.nacidshared.web.repository.BaseRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.base.NomenclatureEntityBase;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.base.BaseNomenclatureFilterDTO;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface NomenclatureBaseRepository<ID extends Serializable, E extends NomenclatureEntityBase<ID>, F extends BaseNomenclatureFilterDTO<ID>> extends BaseRepository<E, ID>, NomenclatureSearchBaseRepository<ID, E, F> {
    List<NomenclatureEntityBase<ID>> getAllByActiveOrderByNameAsc(Integer active);
    List<NomenclatureEntityBase<ID>> getAllByOrderByNameAsc();
}
