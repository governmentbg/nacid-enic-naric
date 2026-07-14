package bg.duosoft.nacidcoreapi.repository.nomenclatures;

import bg.duosoft.nacidcoreapi.repository.BaseRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CfgGraduationWayEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CfgGraduationWayEntityPK;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.12.2022
 * Time: 13:58
 */
public interface CfgGraduationWayRepository extends BaseRepository<CfgGraduationWayEntity, CfgGraduationWayEntityPK> {

    List<CfgGraduationWayEntity> findAllByOrderByGraduationWay_IndexAsc();
}
