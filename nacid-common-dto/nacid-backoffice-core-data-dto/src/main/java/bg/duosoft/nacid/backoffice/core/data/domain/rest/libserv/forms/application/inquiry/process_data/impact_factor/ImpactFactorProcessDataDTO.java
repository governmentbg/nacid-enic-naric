package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.inquiry.process_data.impact_factor;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.inquiry.process_data.InquiryProcessDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImpactFactorProcessDataDTO extends InquiryProcessDataDTO {
    private String impactFactorSearchTypeCode;
}
