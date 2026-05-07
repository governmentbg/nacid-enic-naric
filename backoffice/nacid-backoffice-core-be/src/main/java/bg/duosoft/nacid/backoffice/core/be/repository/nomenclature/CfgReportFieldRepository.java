package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgReportFieldEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * User: ggeorgiev
 * Date: 02.11.2022
 * Time: 12:13
 */
public interface CfgReportFieldRepository extends BaseRepository<CfgReportFieldEntity, String> {
    @Query(value = "SELECT COUNT(*) FROM nomenclatures.cfg_report_field e WHERE e.code = ?1 AND e.sql_code != ?2 ", nativeQuery = true)
    Integer countFieldsWithExcludedSqlCode(String code, String sqlCode);
}
