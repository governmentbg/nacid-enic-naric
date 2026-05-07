package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import lombok.Data;

@Data
public class AttachmentDTO {
    private Integer id;
    private String fileName;
    private Integer fileSize;
    private String contentType;
    private String fileLocation;
    private String bucketName;

}
