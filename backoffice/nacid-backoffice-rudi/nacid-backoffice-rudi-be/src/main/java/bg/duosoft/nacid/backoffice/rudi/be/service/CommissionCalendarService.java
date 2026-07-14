package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachmentDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CalendarProcessDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CommissionCalendarDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.VCommissionCalendarDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.calendar.CalendarProtocolsDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.CommissionCalendarFilterDTO;
import bg.duosoft.nacidshared.web.service.CrudServiceBase;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommissionCalendarService extends CrudServiceBase<Integer, CommissionCalendarDTO> {
    Integer getMaxSessionNum();

    List<VCommissionCalendarDTO> searchRecords(CommissionCalendarFilterDTO filter);

    String getSecretary(Integer calendarId);
    int getRecordsCount(CommissionCalendarFilterDTO filter);

    public boolean existsById(Integer id);

    public String getFullNumber(Integer id);

    CalendarProcessDataDTO getProcessData(Integer calendarId, Integer applicationId);

    void saveProcessData(CalendarProcessDataDTO processData);

    Integer selectLastCommissionSessionNumByApnId(Integer applicationId);

    AttachmentDTO getCalendarProtocol(Integer calendarId);
    CalendarProtocolsDTO getCalendarProtocols(Integer calendarId);

    AttachmentDTO updateProtocol(Integer calendarId, AttachmentDTO protocol);

    CalendarProtocolsDTO updateProtocols(Integer calendarId, CalendarProtocolsDTO protocols);

}
