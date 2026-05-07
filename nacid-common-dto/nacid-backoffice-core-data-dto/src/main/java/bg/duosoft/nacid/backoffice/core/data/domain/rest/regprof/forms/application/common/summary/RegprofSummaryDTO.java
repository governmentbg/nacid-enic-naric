package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.forms.application.common.summary;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.summary.BaseAppSummaryDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegprofSummaryDTO extends BaseAppSummaryDTO {
    private String imiCorrespondence;
    private LocalDate backofficeDate;
}
