package bg.duosoft.nacidservicesbe.service.impl;

import bg.duosoft.nacidcoredata.mapper.ApplicationSubtypeMapper;
import bg.duosoft.nacidcoredata.mapper.FoApplicationStatusMapper;
import bg.duosoft.nacidfrontofficedto.services.common.application.*;
import bg.duosoft.nacidservicesbe.domain.entity.common.*;
import bg.duosoft.nacidservicesbe.mapper.common.application.ApplicationDetailsForSignMapper;
import bg.duosoft.nacidservicesbe.mapper.common.application.ApplicationListFilterMapper;
import bg.duosoft.nacidservicesbe.mapper.common.application.ApplicationListRecordMapper;
import bg.duosoft.nacidservicesbe.mapper.common.application.ApplicationMultipleRecordMapper;
import bg.duosoft.nacidservicesbe.repository.common.ApplicationRepository;
import bg.duosoft.nacidservicesbe.repository.common.MultipleApplicationRepository;
import bg.duosoft.nacidservicesbe.repository.common.VwApplicationRepository;
import bg.duosoft.nacidservicesbe.service.CommonApplicationService;
import bg.duosoft.nacidservicesbe.service.FileService;
import bg.duosoft.nacidservicesbe.utils.AppNumberUtils;
import bg.duosoft.nacidservicesbe.utils.AppStatusUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.12.2022
 * Time: 15:28
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CommonApplicationServiceImpl implements CommonApplicationService {

    private final ApplicationRepository applicationRepository;
    private final VwApplicationRepository vwApplicationRepository;
    private final MultipleApplicationRepository multipleApplicationRepository;
    private final ApplicationListFilterMapper applicationFilterMapper;
    private final ApplicationListRecordMapper applicationListRecordMapper;
    private final ApplicationSubtypeMapper applicationSubtypeMapper;
    private final FoApplicationStatusMapper foApplicationStatusMapper;
    private final ApplicationDetailsForSignMapper applicationDetailsForSignMapper;
    private final ApplicationMultipleRecordMapper applicationMultipleRecordMapper;
    private final FileService fileService;

    @Override
    public List<ApplicationListRecordDTO> getAllApplications(ApplicationListFilterDTO applicationListFilter) {
        ApplicationFilter filter = applicationFilterMapper.toEntity(applicationListFilter);
        List<VwApplicationEntity> entityList = vwApplicationRepository.filterApplications(filter);

        return applicationListRecordMapper.toDtoList(entityList);
    }

    @Override
    public ApplicationListRecordDTO getApplicationById(Integer id) {
        Optional<VwApplicationEntity> appOpt = vwApplicationRepository.findById(id);
        if (appOpt.isPresent()) {
            return applicationListRecordMapper.toDto(appOpt.get());
        }
        return null;
    }

    @Override
    public Integer getTotalApplications(ApplicationListFilterDTO applicationListFilter) {
        ApplicationFilter filter = applicationFilterMapper.toEntity(applicationListFilter);
        return vwApplicationRepository.countFilteredApplications(filter);
    }

    @Override
    public ApplicationSubtype getApplicationSubtype(String dossierNumber, String accessCode) {
        Object[] dossierParts = AppNumberUtils.breakDossierNumber(dossierNumber);
        return applicationSubtypeMapper.toDto(vwApplicationRepository.getApplicationSubtypeCode((String) dossierParts[0], (LocalDate) dossierParts[1], accessCode, AppStatusUtils.getCheckupAllowedStatusCodes()));
    }

    @Override
    public ApplicationSubtype getApplicationSubtype(Integer id) {
        return applicationSubtypeMapper.toDto(applicationRepository.getApplicationSubtypeCode(id));
    }

    @Override
    public String getApplicationTempNumber(Integer id) {
        return applicationRepository.getApplicationTempNumber(id);
    }

    @Override
    public String getApplicationUserCreated(Integer id) {
        return applicationRepository.getApplicationUserCreated(id);
    }

    @Override
    public boolean applicationHasFoStatus(Integer id, FoApplicationStatus foApplicationStatus) {
        return applicationRepository.countFoStatusesForCode(id, foApplicationStatusMapper.toEntity(foApplicationStatus)) > 0;
    }

    @Override
    public FoApplicationStatus getFoStatus(Integer id) {
        return foApplicationStatusMapper.toDto(vwApplicationRepository.getFoStatusCode(id));
    }

    @Override
    public boolean userIsOwner(Integer id, String username) {
        return applicationRepository.existsByIdAndUserCreated(id, username);
    }

    @Override
    public ApplicationDetailsForSignDTO getApplicationSignDetails(Integer id) {
        Optional<ApplicationEntity> app = applicationRepository.findById(id);
        if (app.isPresent()) {
            return applicationDetailsForSignMapper.toDto(app.get());
        } else {
            return null;
        }
    }

    @Override
    public LocalDate getApplicationDateCreated(Integer id) {
        return applicationRepository.getApplicationDateCreated(id);
    }

    public List<String> getAllLastStatusesByUser(String user) {
        return vwApplicationRepository.getAllLastStatusesByUser(user);
    }

    @Override
    public List<ApplicationMultipleRecordDTO> getRelatedAppsFromMultiple(Integer singleApplicationId) {
        List<ApplicationMultipleRecordDTO> list = new ArrayList<>();
        List<MultipleApplicationEntity> multipleApps = multipleApplicationRepository.findMultipleApplicationsForApplicationId(singleApplicationId);
        if (multipleApps != null && multipleApps.size() > 0) {
            multipleApps.stream().forEach(ma -> {
                list.addAll(applicationMultipleRecordMapper.toDtoList(ma.getApplications().stream().filter(app -> !app.getId().equals(singleApplicationId)).collect(Collectors.toList())));
            });
        }

        return list;
    }

    @Override
    public void changePaidFlag(String tempNumber, Boolean paid) {
        applicationRepository.updatePaidFlag(Boolean.TRUE.equals(paid) ? 1 : 0, tempNumber);
    }

    @Override
    public byte[] getAcceptedReceipt(Integer id) {
        byte[] receiptBytes;

        Optional<ApplicationEntity> appOpt = applicationRepository.findById(id);
        if (appOpt.isPresent() && appOpt.get().getReceipts() != null) {
            Optional<ApplicationReceiptEntity> acceptedReceiptOpt = appOpt.get().getReceipts().stream().filter(rec -> rec.getStatusCode().equals(FoApplicationStatus.ACCEPTED.getCode()) && rec.getActive() == 1).findFirst();
            if (acceptedReceiptOpt.isPresent()) {
                ApplicationReceiptEntity acceptedReceipt = acceptedReceiptOpt.get();
                receiptBytes = fileService.getFileContent(acceptedReceipt.getRootDirectory(), acceptedReceipt.getRelativePath(), acceptedReceipt.getFileId(), null);
            } else {
                throw new RuntimeException("Application does not have accepted receipt");
            }
        } else {
            throw new RuntimeException("No such application");
        }

        return receiptBytes;
    }

    @Override
    public boolean applicationCanBeDeleted(Integer id) {
        List<AppStatusHistoryEntity> history = applicationRepository.getApplicationStatusHistory(id);
        if (history != null && history.stream().filter(st -> st.getFoStatus() != null && !st.getFoStatus().getPk().getId().equals(FoApplicationStatus.DRAFT.getCode())).count() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public Integer getApplicationIdForDossierNumberAccessCode(String dossierNumber, String accessCode) {
        Object[] dossierParts = AppNumberUtils.breakDossierNumber(dossierNumber);
        return applicationRepository.getApplicationIdForDossierNumberAccessCode((String) dossierParts[0], (LocalDate) dossierParts[1], accessCode);
    }

    //TODO upload signed application
}
