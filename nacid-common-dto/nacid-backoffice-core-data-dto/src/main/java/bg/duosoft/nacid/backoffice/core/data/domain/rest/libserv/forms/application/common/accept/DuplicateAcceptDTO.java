package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.common.accept;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class DuplicateAcceptDTO {
    private Integer applicationId;
    private String subtypeCode;
    private String entryNumber;
    private LocalDate entryDate;
    private String certificateNumber;
    private String additionalInfo;
}
