package bg.duosoft.nacid.clients.signature.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FileInfo {
    private String signStatus;
    private LocalDateTime customerSignedUploadDate;
    private String signedFileName;
    private String description;
    private String customerSignedFileName;
    private String relativePath;
    private String rootDirectoryPath;
}
