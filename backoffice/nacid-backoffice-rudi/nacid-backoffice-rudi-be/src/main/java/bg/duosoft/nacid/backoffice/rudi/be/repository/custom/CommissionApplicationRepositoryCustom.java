package bg.duosoft.nacid.backoffice.rudi.be.repository.custom;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.VRudiCommissionApplicationsEntity;

import java.util.List;

public interface CommissionApplicationRepositoryCustom {

    List<VRudiCommissionApplicationsEntity> selectApplicationsByCalendarAndAppId(List<Integer> ids, Integer calendarId, String sortColumn, Boolean ascOrder);
}
