package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CommissionParticipationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.CommissionCalendarParticipationCustomDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.CommissionCalendarParticipationSaveDTO;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.CommissionParticipationEntity;
import bg.duosoft.nacidshared.web.service.CrudServiceBase;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommissionParticipationService extends CrudServiceBase<Integer, CommissionParticipationDTO> {
    void saveMembers(CommissionCalendarParticipationSaveDTO dto);
    List<CommissionParticipationDTO> selectByCalendarId(Integer calendarId);

    CommissionParticipationDTO selectByCalendarAndMemberId(Integer calendarId,Integer memberId);
}
