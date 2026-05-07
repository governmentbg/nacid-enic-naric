package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachmentDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommissionCalendarDTO {
    private Integer id;
    private Integer sessionNum;
    private LocalDateTime sessionTime;
    private String notes;
    private String userCreated;
    private LocalDateTime dateCreated;
    private String secretary;
    private ReferenceDataDTO status;
    private List<CommissionApplicationDTO> applications;
    private List<CommissionParticipationDTO> participations;
    private AttachmentDTO commissionProtocol;
    private AttachmentDTO scannedCommissionProtocol;
}
