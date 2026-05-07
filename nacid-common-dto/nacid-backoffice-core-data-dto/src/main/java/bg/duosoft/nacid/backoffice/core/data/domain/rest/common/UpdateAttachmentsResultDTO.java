package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAttachmentsResultDTO {
    private String certificateNumber;
    private String fileUUID;
    private boolean isNewCertificate;
}
