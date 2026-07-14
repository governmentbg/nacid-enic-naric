package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CommissionApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiCommissionApplicationsDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.CommissionCalendarApplicationSaveDTO;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.VRudiCommissionApplicationsEntity;
import bg.duosoft.nacidshared.web.service.CrudServiceBase;

import java.util.List;


public interface CommissionApplicationService extends CrudServiceBase<Integer, CommissionApplicationDTO> {
    void saveApplications(CommissionCalendarApplicationSaveDTO dto);

    CommissionApplicationDTO selectByCalendarAndApplicationId(Integer calendarId, Integer applicationId);

    List<CommissionApplicationDTO> selectByApplicationId(Integer applicationId);

    List<CommissionApplicationDTO> selectByCalendarId(Integer calendarId);

    List<RudiCommissionApplicationsDTO> selectApplicationsByCalendarAndAppId(List<Integer> ids, Integer calendarId, String sortColumn, Boolean ascOrder);

    void updateCommissionApplicationAttachedDoc(Integer calendarId, Integer applicationId, AttachedDocDTO attachedDoc);

}
