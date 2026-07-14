package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.forms.application.examination.parts.status;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.status.RudiRegprofStatusDataBaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegprofStatusDataDTO extends RudiRegprofStatusDataBaseDTO {
    private String imiCorrespondence;
}
