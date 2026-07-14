package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.summary;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.summary.BaseAppSummaryDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LibservSummaryDTO extends BaseAppSummaryDTO {
    private List<MultipleAppDTO> multipleApps;
}
