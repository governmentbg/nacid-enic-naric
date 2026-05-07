package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.core.client.client.common.report.ReportClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.MultiApplicationsReportRequestDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReportTemplate;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReportType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationsDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.RudiApplicationsFilterDTO;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.ApplicationsMapper;
import bg.duosoft.nacid.backoffice.rudi.be.repository.ApplicationsRepository;
import bg.duosoft.nacid.backoffice.rudi.be.service.ApplicationsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationsServiceImpl implements ApplicationsService {

    private final ApplicationsRepository repository;
    private final ApplicationsMapper mapper;
    private final ReportClient reportClient;

    @Override
    public List<RudiApplicationsDTO> searchRecords(RudiApplicationsFilterDTO filter) {
        return mapper.toDtoList(repository.searchRecords(filter));
    }

    @Override
    public List<RudiApplicationsDTO> selectApplicationsByIdsAndSort(List<Integer> ids, String sortColumn, Boolean ascOrder) {
        return mapper.toDtoList(repository.selectApplicationsByIdsAndSort(ids, sortColumn, ascOrder));
    }

    @Override
    public List<RudiApplicationsDTO> selectAllByTypeAndStatus(String ateCode, String aseCode, String apnStatusCode) {
        return mapper.toDtoList(repository.selectAllByTypeAndStatus(ateCode, aseCode, apnStatusCode));
    }

    @Override
    public ResponseEntity<byte[]> generateReport(RudiApplicationsFilterDTO filter) {
        String templateName = getTemplateName(filter);

        if (StringUtils.hasText(templateName)) {
            List<Integer> applicationIds = repository.selectApplicationIds(filter);

            if (!CollectionUtils.isEmpty(applicationIds)) {
                try {
                    return reportClient.generateMultiApplicationsReport(new MultiApplicationsReportRequestDTO(templateName, ReportType.XLSX, applicationIds));
                } catch (Exception e) {
                    log.error("Cannot generate rudi applications report file for appSubType = " + filter.getAteCode() + "-" + filter.getAseCode());
                    throw new RuntimeException("Cannot generate rudi applications report file !", e);
                }
            }
        }

        return ResponseEntity.notFound().build();
    }

    private String getTemplateName(RudiApplicationsFilterDTO filter) {
        if (!StringUtils.hasText(filter.getAteCode()) || !StringUtils.hasText(filter.getAseCode())) {
            return null;
        }

        ApplicationSubType appSubType = ApplicationSubType.selectByTypeAndSubType(filter.getAteCode(), filter.getAseCode());
        return switch (appSubType) {
            case RUDI_SAR -> ReportTemplate.APP_REPORT_RUDI_SAR.template();
            case RUDI_DOC_DEGREE_RECOGNITION -> ReportTemplate.APP_REPORT_RUDI_DOCREC.template();
            case RUDI_UNI_DIPLOMA_RECOGNITION -> ReportTemplate.APP_REPORT_RUDI_UDIREC.template();
            default -> null;
        };
    }

    @Override
    public int getRecordsCount(RudiApplicationsFilterDTO filter) {
        return repository.getRecordsCount(filter);
    }

    @Override
    public List<Integer> selectAllApplicationIdsByCalendarId(Integer calendarId) {
        return repository.selectAllApplicationIdsByCalendarId(calendarId);
    }

    @Override
    public List<RudiApplicationsDTO> selectApplicationsByIds(List<Integer> ids) {
        return mapper.toDtoList(repository.selectApplicationsByIds(ids));
    }

    @Override
    public RudiApplicationsDTO selectApplicationsById(Integer id) {
        return mapper.toDto(repository.selectApplicationsById(id));
    }

}
