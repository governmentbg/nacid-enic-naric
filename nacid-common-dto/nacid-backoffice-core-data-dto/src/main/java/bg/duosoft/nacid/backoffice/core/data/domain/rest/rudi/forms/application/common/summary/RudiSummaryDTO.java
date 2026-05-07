package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.summary;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.summary.BaseAppSummaryDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RudiSummaryDTO extends BaseAppSummaryDTO {
    private LocalDate backofficeDate;
}
