package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.inquiry.main;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.common.main.LibservMainDataBaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquiryMainDataDTO extends LibservMainDataBaseDTO {
    private String applicantTitleBefore;
    private String applicantTitleAfter;
}
