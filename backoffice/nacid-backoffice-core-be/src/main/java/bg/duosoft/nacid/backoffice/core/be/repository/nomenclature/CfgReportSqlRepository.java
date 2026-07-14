package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.CfgReportSqlRepositoryCustom;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgReportSqlEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * User: ggeorgiev
 * Date: 02.11.2022
 * Time: 12:13
 */
public interface CfgReportSqlRepository extends BaseRepository<CfgReportSqlEntity, String>, CfgReportSqlRepositoryCustom {
    public List<CfgReportSqlEntity> findAllByGroupFlag(Integer groupFlag);
    public List<CfgReportSqlEntity> findAllByIdInAndGroupFlag(Collection<String> ids, Integer groupFlag);
    @Query("select distinct s from CfgReportSqlEntity s join s.fields f where f.id in :fieldNames")
    public List<CfgReportSqlEntity> findAllByFieldNames(@Param("fieldNames") Collection<String> fieldNames);
}
