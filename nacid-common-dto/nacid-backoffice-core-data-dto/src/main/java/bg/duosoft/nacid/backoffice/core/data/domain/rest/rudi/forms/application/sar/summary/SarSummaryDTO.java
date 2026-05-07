package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.sar.summary;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.summary.BaseAppSummaryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.summary.RudiSummaryDTO;
import lombok.*;

@Getter
@Setter
public class SarSummaryDTO extends RudiSummaryDTO {

    private String diplomaOwner;
    private SarFlagColorCodeDTO sarFlagColorCode;

}
