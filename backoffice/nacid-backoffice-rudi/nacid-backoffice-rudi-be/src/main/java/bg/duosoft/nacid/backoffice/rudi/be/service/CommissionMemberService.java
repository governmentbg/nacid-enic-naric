package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CommissionMemberDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.CommissionMemberFilterDTO;
import bg.duosoft.nacidshared.web.service.CrudServiceBase;

import java.util.List;

public interface CommissionMemberService extends CrudServiceBase<Integer, CommissionMemberDTO> {

    List<CommissionMemberDTO> searchRecords(CommissionMemberFilterDTO filter);

    int getRecordsCount(CommissionMemberFilterDTO filter);

    void toggleActivation(Integer id);
    List<Integer> selectAllCommissionMemberIdsByCalendarId(Integer calendarId);
    List<CommissionMemberDTO> selectMembersByIds(List<Integer> ids);

    List<CommissionMemberDTO> selectMembersByPosition(String position);

}
