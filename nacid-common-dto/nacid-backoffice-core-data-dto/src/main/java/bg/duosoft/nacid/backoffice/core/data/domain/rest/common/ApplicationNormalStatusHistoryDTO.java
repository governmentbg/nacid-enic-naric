package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.LegalReasonDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicationNormalStatusHistoryDTO {
    private Integer id;
    private NormalStatusDTO status;
    private LegalReasonDTO legalReason;
    private LocalDateTime dateCreated;
    private String userCreated;
}
