package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;

import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationsService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.NormalStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgAppStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationStatusService {
    public final CfgAppStatusService cfgAppStatusService;
    public final ApplicationsService applicationService;

    public List<ReferenceDataDTO> selectCommissionStatuses(boolean onlyActive) {
        List<CfgAppStatusDTO> commissionStatuses = cfgAppStatusService.selectCommissionStatuses(onlyActive);
        return commissionStatuses.stream().map(ApplicationStatusService::selectOverridedReferenceData).distinct().collect(Collectors.toList());
    }

    public List<ReferenceDataDTO> selectLegalStatuses(String applicationType, String applicationSubType, boolean onlyActive) {
        List<CfgAppStatusDTO> legalStatuses = cfgAppStatusService.selectLegalStatuses(applicationType, applicationSubType, onlyActive);
        return legalStatuses.stream().map(ApplicationStatusService::selectOverridedReferenceData).distinct().collect(Collectors.toList());
    }

    public List<ReferenceDataDTO> selectByApplicationType(String applicationType, boolean onlyActive) {
        List<CfgAppStatusDTO> applicationStatuses = cfgAppStatusService.selectByApplicationType(applicationType, onlyActive);
        return applicationStatuses.stream().map(ApplicationStatusService::selectOverridedReferenceData).distinct().collect(Collectors.toList());
    }

    public List<ReferenceDataDTO> selectByApplicationTypeAndSubType(String applicationType, String applicationSubType, boolean onlyActive) {
        List<CfgAppStatusDTO> applicationStatuses = cfgAppStatusService.selectByApplicationTypeAndSubType(applicationType, applicationSubType, onlyActive);
        return applicationStatuses.stream().map(ApplicationStatusService::selectOverridedReferenceData).distinct().collect(Collectors.toList());
    }

    public List<NormalStatusDTO> selectNormalStatuses(Integer applicationId, boolean onlyActive) {
        ApplicationDTO application = applicationService.getApplicationById(applicationId);
        if (Objects.isNull(application)) {
            throw new ResourceNotFoundException();
        }

        String applicationType = application.getApplicationType().getId();
        String applicationSubtype = application.getApplicationSubtype().getId();
        String currentStatus = application.getStatus().getId();

        List<CfgAppStatusDTO> normalStatuses = cfgAppStatusService.selectNormalStatuses(applicationType, applicationSubtype, onlyActive, currentStatus);
        return normalStatuses.stream().map(this::createNormalStatus).distinct().collect(Collectors.toList());
    }

    private NormalStatusDTO createNormalStatus(CfgAppStatusDTO cfgAppStatus) {
        ReferenceDataDTO status = selectOverridedReferenceData(cfgAppStatus);
        return new NormalStatusDTO(status, cfgAppStatus.getIsLegal());
    }

    private static ReferenceDataDTO selectOverridedReferenceData(CfgAppStatusDTO cfgAppStatus) {
        ReferenceDataDTO status = cfgAppStatus.getStatus();

        if (Objects.nonNull(status) && Objects.nonNull(status.getIsActive())) {
            status.setIsActive(cfgAppStatus.getIsActive());
        }
        return status;
    }
}
