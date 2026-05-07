package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.inquiry.process_data.citation;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.inquiry.process_data.InquiryProcessDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CitationProcessDataDTO extends InquiryProcessDataDTO {
    private String citingSearchTypeCode;
}
