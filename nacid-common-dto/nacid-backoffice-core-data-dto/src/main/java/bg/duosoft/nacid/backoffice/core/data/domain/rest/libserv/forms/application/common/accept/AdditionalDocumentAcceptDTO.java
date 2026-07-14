package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.common.accept;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class AdditionalDocumentAcceptDTO {
    private Integer parentApnId;
    private String parentSubtypeCode;
    private String parentEntryNumber;
    private String additionalDocumentEntryNumber;
}
