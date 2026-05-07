package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.cfgappstatus.CfgAppStatusClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationStatusHistoryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.InsertStatusResultDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.SarApplicationType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgSarAppStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.SarApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.service.ApplicationSarStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationSarStatusServiceImpl implements ApplicationSarStatusService {
    private final CfgAppStatusClient cfgAppStatusClient;

    public void fillFinalSarStatuses(InsertStatusResultDTO insertStatusResult, RudiApplicationDTO application) {
        ApplicationSubType applicationSubType = ApplicationSubType.selectByTypeAndSubType(application.getApplication().getApplicationType().getId(), application.getApplication().getApplicationSubtype().getId());
        if (applicationSubType == ApplicationSubType.RUDI_SAR) {
            ApplicationStatusHistoryDTO statusHistory = insertStatusResult.getStatus();
            if (Objects.nonNull(statusHistory) && Objects.nonNull(statusHistory.getStatus())) {
                ReferenceDataDTO statusData = statusHistory.getStatus();

                if (StringUtils.hasText(statusData.getId())) {
                    String statusCode = statusData.getId();
                    List<CfgSarAppStatusDTO> sarConfig = cfgAppStatusClient.getSarConfigsByStatus(statusCode);

                    if (!CollectionUtils.isEmpty(sarConfig)) {
                        SarApplicationDTO sarApplication = application.getSarApplication();
                        if (Objects.nonNull(sarApplication)) {
                            if (sarApplication.getIsStatute()) {
                                boolean hasFinalStatus = sarConfig.stream().anyMatch(config -> SarApplicationType.STATUTE.code().equals(config.getSarApplicationType().getId()));
                                if (hasFinalStatus) {
                                    sarApplication.setStatuteFinalStatus(statusHistory);
                                }
                            }

                            if (sarApplication.getIsAuthenticity()) {
                                boolean hasFinalStatus = sarConfig.stream().anyMatch(config -> SarApplicationType.AUTHENTICITY.code().equals(config.getSarApplicationType().getId()));
                                if (hasFinalStatus) {
                                    sarApplication.setAuthenticityFinalStatus(statusHistory);
                                }
                            }

                            if (sarApplication.getIsRecommendation()) {
                                boolean hasFinalStatus = sarConfig.stream().anyMatch(config -> SarApplicationType.RECOMMENDATION.code().equals(config.getSarApplicationType().getId()));
                                if (hasFinalStatus) {
                                    sarApplication.setRecommendationFinalStatus(statusHistory);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
