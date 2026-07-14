package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import lombok.Data;
import java.util.UUID;

@Data
public class ApplicationCertificatesDTO {
    private Integer id;
    private String certificateNumber;
    private String uuid;
    private String certificateStatus;
    private Integer applicationAttachedDocId;
}
