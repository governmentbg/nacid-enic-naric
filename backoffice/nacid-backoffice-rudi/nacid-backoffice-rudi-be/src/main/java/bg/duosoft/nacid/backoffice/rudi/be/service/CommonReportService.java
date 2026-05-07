package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationsDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.RudiCommonReportFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.service.BaseReportService;
import org.springframework.http.ResponseEntity;

/**
 * User: ggeorgiev
 * Date: 05.09.2023
 * Time: 14:08
 */
public interface CommonReportService extends BaseReportService<RudiApplicationsDTO, RudiCommonReportFilterDTO> {
    ResponseEntity<byte[]> generateReport(RudiCommonReportFilterDTO filter);
}
