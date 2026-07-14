package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.udirec.status;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.UdirecDocrecStatusDataCommonDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UdirecStatusDataDTO extends UdirecDocrecStatusDataCommonDTO {
    private String recognizedQualification;
    private List<String> recognizedSpecialities;
}
