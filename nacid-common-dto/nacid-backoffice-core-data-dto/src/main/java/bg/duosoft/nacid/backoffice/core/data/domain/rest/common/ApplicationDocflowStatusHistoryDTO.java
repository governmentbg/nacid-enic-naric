package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicationDocflowStatusHistoryDTO {
    private Integer id;
    private ReferenceDataDTO docflowStatus;
    private LocalDateTime dateCreated;
    private String userCreated;
}
