package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgReportSqlEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CfgReportSqlFilterDTO;

import java.util.List;

public interface CfgReportSqlRepositoryCustom {
    List<CfgReportSqlEntity> selectReportSqlData(CfgReportSqlFilterDTO filter);
    int selectReportSqlDataCount(CfgReportSqlFilterDTO filter);
}
