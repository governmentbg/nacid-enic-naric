package bg.duosoft.nacid.backoffice.rudi.be.repository.custom;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.CommissionCalendarFilterDTO;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.VCommissionCalendarEntity;
import bg.duosoft.nacid.backoffice.rudi.be.domain.query_result.CalendarProcessDataQR;

import java.util.List;

public interface CommissionCalendarRepositoryCustom {

    List<VCommissionCalendarEntity> searchRecords(CommissionCalendarFilterDTO filter);

    int getRecordsCount(CommissionCalendarFilterDTO filter);

    CalendarProcessDataQR getProcessData(Integer calendarId, Integer applicationId);
}
