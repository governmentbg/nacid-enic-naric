package bg.duosoft.nacidcoreapi.repository.nomenclatures;

import bg.duosoft.nacidcoreapi.repository.BaseRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CfgEduLevelEntity;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.CfgEduLevelEntityPK;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.12.2022
 * Time: 16:30
 */
public interface CfgEduLevelRepository extends BaseRepository<CfgEduLevelEntity, CfgEduLevelEntityPK> {

    List<CfgEduLevelEntity> findAllByOrderByEduLevel_IndexAsc();
    List<CfgEduLevelEntity> findAllById_ApplicationTypeCodeAndId_ApplicationSubtypeCodeOrderByEduLevel_IndexAsc(String applicationTypeCode, String applicationSubtypeCode);
}
