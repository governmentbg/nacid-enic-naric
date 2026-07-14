package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NormalStatusDTO extends ReferenceDataDTO {
    private Boolean isLegalStatus;

    public NormalStatusDTO(ReferenceDataDTO status, Boolean isLegalStatus) {
        super(status);
        this.isLegalStatus = isLegalStatus;
    }
}
