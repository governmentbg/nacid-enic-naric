package bg.duosoft.nacidbackofficeshareddata.service.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationDocflowStatusHistoryEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationStatusHistoryEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.LegalReasonEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.ApplicationDocflowStatusHistoryMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.ApplicationStatusHistoryMapper;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityGroup;
import bg.duosoft.nacidbackofficeshareddata.repository.BaseApplicationRepository;
import bg.duosoft.nacidbackofficeshareddata.repository.BaseStatusRepository;
import bg.duosoft.nacidbackofficeshareddata.service.BaseStatusService;
import bg.duosoft.nacidfrontofficedto.user.BaseUserDetailsDTO;
import bg.duosoft.nacidfrontofficedto.user.NacidUserDetailsDTO;
import bg.duosoft.nacidkeycloakservices.service.KeycloakUserService;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class BaseStatusServiceImpl implements BaseStatusService {

    private final EntityManager entityManager;
    private final BaseStatusRepository baseStatusRepository;
    private final BaseApplicationRepository baseApplicationRepository;
    private final ApplicationStatusHistoryMapper statusHistoryMapper;
    private final ApplicationDocflowStatusHistoryMapper docflowStatusHistoryMapper;
    private final IntegerToBooleanMapper integerToBooleanMapper;
    private final KeycloakUserService keycloakUserService;


    @Override
    public InsertStatusResultDTO insertStatus(InsertStatusDTO insertStatusData) {
        String user = SecurityUtils.getUsername();
        Integer applicationId = insertStatusData.getApplicationId();
        ApplicationEntity application = baseApplicationRepository.selectApplicationById(applicationId);

        ApplicationStatusHistoryEntity status = null;
        String insertStatus = insertStatusData.getStatusId();
        if (StringUtils.hasText(insertStatus)) {
            String currentStatus = application.getStatus().getPk().getId();
            if (!currentStatus.equals(insertStatus)) {
                status = insertStatus(application, insertStatusData, user);
            } else {
                status = updateStatusHistoryRecord(insertStatusData, applicationId);
            }
        }

        ApplicationDocflowStatusHistoryEntity docflowStatus = null;
        String insertDocflowStatus = insertStatusData.getDocflowStatusId();
        if (StringUtils.hasText(insertDocflowStatus)) {
            String currentDocflowStatus = application.getDocflowStatus().getPk().getId();
            if (!currentDocflowStatus.equals(insertStatusData.getDocflowStatusId())) {
                docflowStatus = insertDocflowStatus(application, insertStatusData, user);
            }
        }

        entityManager.persist(application);

        return InsertStatusResultDTO.builder().status(statusHistoryMapper.toDto(status)).docflowStatus(docflowStatusHistoryMapper.toDto(docflowStatus)).build();
    }

    @Override
    public void insertInitialStatusHistoryRecords(InsertStatusDTO insertStatusData, String user) {
        persistHistory(insertStatusData, user);
        persistDocflowStatusHistory(user, insertStatusData);
    }

    @Override
    public List<ApplicationNormalStatusHistoryDTO> selectNormalStatusHistoryByApplicationId(Integer applicationId, String applicationType, String applicationSubtype) {
        List<ApplicationStatusHistoryDTO> statusHistoryList = statusHistoryMapper.toDtoList(baseStatusRepository.selectStatusHistoryByApplicationId(applicationId));

        List<ApplicationNormalStatusHistoryDTO> normalStatusHistoryList = new ArrayList<>();
        for (ApplicationStatusHistoryDTO history : statusHistoryList) {
            Integer legalFlag = baseStatusRepository.selectLegalFlagByTypeSubtypeStatusCode(applicationType, applicationSubtype, history.getStatus().getId());

            ApplicationNormalStatusHistoryDTO normalStatusHistory = new ApplicationNormalStatusHistoryDTO();
            normalStatusHistory.setId(history.getId());
            normalStatusHistory.setStatus(new NormalStatusDTO(history.getStatus(), integerToBooleanMapper.intToBoolean(legalFlag)));
            normalStatusHistory.setLegalReason(history.getLegalReason());
            normalStatusHistory.setDateCreated(history.getDateCreated());
            normalStatusHistory.setUserCreated(getUserCreatedFullName(history.getUserCreated()));
            normalStatusHistoryList.add(normalStatusHistory);
        }

        return normalStatusHistoryList;
    }

    private String getUserCreatedFullName(String userCreated) {
        String userCreatedName = userCreated;
        if (StringUtils.hasText(userCreated)) {
            Map<String, NacidUserDetailsDTO> usersMap = keycloakUserService.getUsersMapFromGroupHierarchyCached(SecurityGroup.BO_USERS);
            if (!CollectionUtils.isEmpty(usersMap)) {
                BaseUserDetailsDTO userDetails = usersMap.get(userCreated);
                if (Objects.nonNull(userDetails)) {
                    userCreatedName = userDetails.getFullName();
                }
            }

        }
        return userCreatedName;
    }

    @Override
    public List<ApplicationDocflowStatusHistoryDTO> selectDocflowStatusHistoryByApplicationId(Integer applicationId) {
        return docflowStatusHistoryMapper.toDtoList(baseStatusRepository.selectDocflowStatusHistoryByApplicationId(applicationId));
    }


    private ApplicationDocflowStatusHistoryEntity insertDocflowStatus(ApplicationEntity application, InsertStatusDTO insertStatusData, String user) {
        ApplicationDocflowStatusHistoryEntity docflowStatusHistory = persistDocflowStatusHistory(user, insertStatusData);
        application.setDocflowStatus(new ReferenceDataEntity(ReferenceDataDomain.DOCFLOW_STATUS, insertStatusData.getDocflowStatusId()));
        return docflowStatusHistory;
    }

    private ApplicationDocflowStatusHistoryEntity persistDocflowStatusHistory(String user, InsertStatusDTO insertStatusData) {
        ApplicationDocflowStatusHistoryEntity docflowHistoryEntity = new ApplicationDocflowStatusHistoryEntity();
        docflowHistoryEntity.setApplicationId(insertStatusData.getApplicationId());
        docflowHistoryEntity.setDocflowStatus(new ReferenceDataEntity(ReferenceDataDomain.DOCFLOW_STATUS, insertStatusData.getDocflowStatusId()));
        docflowHistoryEntity.setDateCreated(LocalDateTime.now());
        docflowHistoryEntity.setUserCreated(user);

        entityManager.persist(docflowHistoryEntity);
        entityManager.refresh(docflowHistoryEntity);

        return docflowHistoryEntity;
    }

    private ApplicationStatusHistoryEntity updateStatusHistoryRecord(InsertStatusDTO insertStatusData, Integer applicationId) {
        ApplicationStatusHistoryEntity historyEntity = baseStatusRepository.selectLastHistoryStatus(applicationId);

        if (Objects.nonNull(historyEntity)) {
            Integer legalReasonId = insertStatusData.getLegalReasonId();
            Integer historyLegalReasonId = Objects.nonNull(historyEntity.getLegalReason()) ? historyEntity.getLegalReason().getId() : null;
            if (!Objects.equals(legalReasonId, historyLegalReasonId)) {
                historyEntity.setLegalReason(Objects.nonNull(legalReasonId) ? new LegalReasonEntity(legalReasonId) : null);
            }

            entityManager.merge(historyEntity);
            entityManager.flush();
            entityManager.refresh(historyEntity);
        }
        return historyEntity;
    }

    private ApplicationStatusHistoryEntity insertStatus(ApplicationEntity application, InsertStatusDTO insertStatusData, String user) {
        ApplicationStatusHistoryEntity createdHistoryRecord = persistHistory(insertStatusData, user);

        String insertStatus = insertStatusData.getStatusId();
        application.setStatus(new ReferenceDataEntity(ReferenceDataDomain.APPLICATION_STATUS, insertStatus));

        Integer legalFlag = baseStatusRepository.selectLegalFlagByTypeSubtypeStatusCode(application.getApplicationType().getId(), application.getApplicationSubtype().getId(), insertStatus);
        if (Objects.nonNull(legalFlag) && legalFlag.equals(1)) {
            application.setFinalStatusHistory(new ApplicationStatusHistoryEntity(createdHistoryRecord.getId()));
        }

        return createdHistoryRecord;
    }

    private ApplicationStatusHistoryEntity persistHistory(InsertStatusDTO insertStatusData, String user) {
        ApplicationStatusHistoryEntity historyEntity = new ApplicationStatusHistoryEntity();
        historyEntity.setApplicationId(insertStatusData.getApplicationId());
        historyEntity.setStatus(new ReferenceDataEntity(ReferenceDataDomain.APPLICATION_STATUS, insertStatusData.getStatusId()));
        historyEntity.setLegalReason(Objects.nonNull(insertStatusData.getLegalReasonId()) ? new LegalReasonEntity(insertStatusData.getLegalReasonId()) : null);
        historyEntity.setCommissionCalendarId(insertStatusData.getCalendarId());
        historyEntity.setDateCreated(LocalDateTime.now());
        historyEntity.setUserCreated(user);

        entityManager.persist(historyEntity);
        entityManager.refresh(historyEntity);
        return historyEntity;
    }
}
