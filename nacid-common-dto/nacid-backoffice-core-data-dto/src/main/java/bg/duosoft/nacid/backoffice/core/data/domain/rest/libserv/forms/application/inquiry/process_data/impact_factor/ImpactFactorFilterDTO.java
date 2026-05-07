package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.inquiry.process_data.impact_factor;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.Pageable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImpactFactorFilterDTO implements Pageable {
    private Integer applicationId;
    private Integer page = this.DEFAULT_PAGE;
    private Integer pageSize = this.DEFAULT_PAGE_SIZE;
}
