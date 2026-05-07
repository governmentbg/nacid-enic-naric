package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.inquiry.reception;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.common.reception.LibservBaseReceptionDTO;
import lombok.Data;
import java.util.List;

@Data
public class InquiryReceptionDTO extends LibservBaseReceptionDTO {
    private List<String> kinds;
    private String inquiryAim;
    private String periodFrom;
    private String periodTo;
    private String previousInquiry;
}
