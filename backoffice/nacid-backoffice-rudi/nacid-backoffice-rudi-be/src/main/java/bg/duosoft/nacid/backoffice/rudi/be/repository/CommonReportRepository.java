package bg.duosoft.nacid.backoffice.rudi.be.repository;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.RudiCommonReportFilterDTO;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.VRudiApplicationsEntity;
import bg.duosoft.nacidbackofficeshareddata.repository.BaseReportRepository;
import bg.duosoft.nacidbackofficeshareddata.service.BaseReportService;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 30.08.2023
 * Time: 17:46
 */
public interface CommonReportRepository extends BaseReportRepository<VRudiApplicationsEntity, RudiCommonReportFilterDTO> {

}
