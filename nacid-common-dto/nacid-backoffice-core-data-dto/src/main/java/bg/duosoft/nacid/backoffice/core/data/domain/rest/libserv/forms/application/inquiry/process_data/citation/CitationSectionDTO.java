package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.inquiry.process_data.citation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CitationSectionDTO {
    private Integer applicationId;
    private Integer publicationPeriodId;
    private Integer publicationId;
    private Integer citationId;
    private String citation;
}
