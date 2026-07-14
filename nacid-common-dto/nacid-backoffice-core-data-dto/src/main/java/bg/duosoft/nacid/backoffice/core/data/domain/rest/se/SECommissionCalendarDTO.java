package bg.duosoft.nacid.backoffice.core.data.domain.rest.se;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachmentDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SECommissionCalendarDTO {
    private Integer id;
    private Integer sessionNum;
    private LocalDate sessionDate;
    private String notes;
    private String userCreated;
    private LocalDateTime dateCreated;
    private ReferenceDataDTO status;
    private AttachmentDTO commissionProtocol;
    private AttachmentDTO scannedCommissionProtocol;
    private AttachmentDTO acceptanceProtocol;
    private AttachmentDTO scannedAcceptanceProtocol;
}
