package bg.duosoft.nacidbackofficeshareddata.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDocflowStatusHistoryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationNormalStatusHistoryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.InsertStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.InsertStatusResultDTO;

import java.util.List;

public interface BaseStatusService {
    InsertStatusResultDTO insertStatus(InsertStatusDTO insertStatusData);

    void insertInitialStatusHistoryRecords(InsertStatusDTO insertStatusData, String user);

    List<ApplicationNormalStatusHistoryDTO> selectNormalStatusHistoryByApplicationId(Integer applicationId, String applicationType, String applicationSubtype);
    List<ApplicationDocflowStatusHistoryDTO> selectDocflowStatusHistoryByApplicationId(Integer applicationId);

}
