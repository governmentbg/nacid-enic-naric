package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacidshared.web.repository.BaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgSarAppStatusEntity;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 29.08.2022
 * Time: 14:37
 */
public interface CfgSarAppStatusRepository extends BaseRepository<CfgSarAppStatusEntity, Integer> {
    public CfgSarAppStatusEntity getBySarApplicationTypePkIdAndStatusPkId(String sarApplicationType, String statusCode);
    List<CfgSarAppStatusEntity> getAllByStatusPkId(String statusCode);
}
