package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.calendar;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachmentDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalendarProtocolsDTO {
    private AttachmentDTO commissionProtocol;
    private AttachmentDTO scannedCommissionProtocol;
    private AttachmentDTO acceptanceProtocol;
    private AttachmentDTO scannedAcceptanceProtocol;
}
