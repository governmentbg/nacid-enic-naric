package bg.duosoft.nacid.backoffice.core.data.domain.rest.se.forms.commission_calendar;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SECalendarMainDataSectionDTO {
    private Integer id;
    private Integer sessionNum;
    private LocalDate sessionDate;
    private String notes;
    private String userCreated;
    private LocalDateTime dateCreated;
    private ReferenceDataDTO status;
}
