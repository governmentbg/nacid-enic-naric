package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.commission_calendar;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CalendarMainDataSectionDTO {
    private Integer id;
    private Integer sessionNum;
    private LocalDateTime sessionTime;
    private String notes;
    private String userCreated;
    private LocalDateTime dateCreated;
    private ReferenceDataDTO status;
}
