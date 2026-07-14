package bg.duosoft.nacid.backoffice.core.data.util.minio;

import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public final class MinioFilePath {

    private final String bucket;
    private final String relativePath;
    private final String fileId;

    private MinioFilePath(String bucket, String relativePath, String fileId) {
        Assert.notNull(bucket, "Bucket must not be null");
        Assert.notNull(relativePath, "Relative path must not be null");
        Assert.notNull(fileId, "File id must not be null");
        this.bucket = bucket;
        this.relativePath = relativePath;
        this.fileId = fileId;
    }


    public static MinioFilePath of(String bucket, String relativePath, String fileId) {
        return new MinioFilePath(bucket, relativePath, fileId);
    }

}
