package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttachmentsViewDTO {

    private Integer applicationId;
    private Integer attachmentId;
    private String docTypeName;
    private String direction;
    private String fileName;
    private String bucketName;
    private String fileLocation;
    private String contentType;

}
