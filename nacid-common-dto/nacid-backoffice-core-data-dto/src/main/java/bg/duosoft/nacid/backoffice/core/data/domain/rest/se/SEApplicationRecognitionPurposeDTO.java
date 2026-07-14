package bg.duosoft.nacid.backoffice.core.data.domain.rest.se;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SEApplicationRecognitionPurposeDTO implements Serializable {
    private Integer id;
    private Integer applicationId;
    private ReferenceDataDTO purpose;
    private String notes;
}
