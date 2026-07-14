package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.inquiry.process_data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquiryProcessDataDTO {
    private Integer applicationId;
    private Integer periodFrom;
    private Integer periodTo;
    private String inquiryNotes;
    private String inquiryAim;
}
