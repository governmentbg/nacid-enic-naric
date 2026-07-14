package bg.duosoft.nacid.backoffice.core.data.domain.rest.se.summary;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.summary.BaseAppSummaryDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SESummaryDTO extends BaseAppSummaryDTO {
    private LocalDate backofficeDate;
    private LocalDate executionPeriodEnd;
}
