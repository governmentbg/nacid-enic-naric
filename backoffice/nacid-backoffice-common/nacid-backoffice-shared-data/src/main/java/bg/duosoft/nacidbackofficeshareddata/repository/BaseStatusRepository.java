package bg.duosoft.nacidbackofficeshareddata.repository;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationDocflowStatusHistoryEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationStatusHistoryEntity;

import java.util.List;

public interface BaseStatusRepository {
    Integer selectLegalFlagByTypeSubtypeStatusCode(String applicationType, String applicationSubtype, String status);

    ApplicationStatusHistoryEntity selectLastHistoryStatus(Integer applicationId);

    List<ApplicationStatusHistoryEntity> selectStatusHistoryByApplicationId(Integer applicationId);

    List<ApplicationDocflowStatusHistoryEntity> selectDocflowStatusHistoryByApplicationId(Integer applicationId);

}
