package bg.duosoft.nacid.backoffice.core.be.service.report;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.file.AppReportResultDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.file.AppReportTemplateDTO;

import java.util.List;

public interface ApplicationReportService {

    List<AppReportResultDTO> generateApplicationReports(AppReportTemplateDTO template);

}
