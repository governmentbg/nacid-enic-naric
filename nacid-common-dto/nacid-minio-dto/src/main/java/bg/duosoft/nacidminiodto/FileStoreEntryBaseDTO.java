package bg.duosoft.nacidminiodto;

import lombok.Data;

import java.util.Map;

@Data
public class FileStoreEntryBaseDTO implements Cloneable {
    private String fileId;
    private String fileName;
    private Long fileSize;
    private String contentType;
    private String rootDirectory;
    private String relativePath;
    private byte[] content;
    private Map<String, String> additionalMetadata;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
