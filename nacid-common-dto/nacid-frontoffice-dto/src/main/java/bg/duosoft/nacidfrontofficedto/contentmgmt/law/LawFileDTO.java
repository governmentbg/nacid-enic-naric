package bg.duosoft.nacidfrontofficedto.contentmgmt.law;

import lombok.Data;

@Data
public class LawFileDTO {
    private String fileId;
    private String contentType;
    private String fileName;
    private String relativePath;
    private String rootDirectory;
}
