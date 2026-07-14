package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.LegalReasonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicationStatusHistoryDTO {
    private Integer id;
    private Integer commissionCalendarId;
    private ReferenceDataDTO status;
    private LegalReasonDTO legalReason;
    private LocalDateTime dateCreated;
    private String userCreated;
}
