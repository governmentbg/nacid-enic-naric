package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.core.client.client.common.report.ReportClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.MultiApplicationsReportRequestDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReportTemplate;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReportType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationsDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.RudiCommonReportFilterDTO;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.ApplicationsMapper;
import bg.duosoft.nacid.backoffice.rudi.be.repository.CommonReportRepository;
import bg.duosoft.nacid.backoffice.rudi.be.service.CommonReportService;
import bg.duosoft.nacidbackofficeshareddata.service.impl.BaseReportServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 05.09.2023
 * Time: 14:05
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CommonReportServiceImpl extends BaseReportServiceImpl<RudiApplicationsDTO, RudiCommonReportFilterDTO> implements CommonReportService{
    private final CommonReportRepository repository;
    private final ApplicationsMapper mapper;
    private final ReportClient reportClient;

    @Override
    protected ApplicationsMapper getMapper() {
        return mapper;
    }

    @Override
    protected CommonReportRepository getRepository() {
        return repository;
    }

    @Override
    public ResponseEntity<byte[]> generateReport(RudiCommonReportFilterDTO filter) {
        List<Integer> reportApplicationIds = repository.getReportApplicationIds(filter);
        if (!CollectionUtils.isEmpty(reportApplicationIds)) {
            try {
                return reportClient.generateMultiApplicationsReport(new MultiApplicationsReportRequestDTO(ReportTemplate.RUDI_COMMON_REPORT.template(), ReportType.XLSX, reportApplicationIds));
            } catch (Exception e) {
                log.error("Cannot generate rudi common report file !");
                throw new RuntimeException(e);
            }
        }

        return ResponseEntity.notFound().build();
    }
}
