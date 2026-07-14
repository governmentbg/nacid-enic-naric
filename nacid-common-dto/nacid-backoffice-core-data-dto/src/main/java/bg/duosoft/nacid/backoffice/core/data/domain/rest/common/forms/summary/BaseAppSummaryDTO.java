package bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.summary;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BaseAppSummaryDTO {
    private Integer id;
    private String applicant;
    private String entryNum;
    private LocalDate entryDate;
    private String responsibleUser;
    private String status;
    private String docflowStatus;
    private String docflowDocumentUrl;
    private Integer efilingId;
    private String serviceTypeCode;
}
