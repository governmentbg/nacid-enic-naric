package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.inquiry.accept;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.common.accept.LibservAcceptBaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquiryAcceptDTO extends LibservAcceptBaseDTO {

    private InquiryAcceptViewDataDTO viewData;

}
