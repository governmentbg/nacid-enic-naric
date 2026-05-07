package bg.duosoft.nacid.backoffice.abdocs.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Getter
@Setter
public class AbdocsMultipartFile implements MultipartFile {

    private byte[] input;
    private String fileName;
    private String mimeType;

    public AbdocsMultipartFile(byte[] content, String fileName) {
        this.input = content;
        this.fileName = fileName;
    }

    public AbdocsMultipartFile(byte[] content, String fileName, String mimeType) {
        this.input = content;
        this.fileName = fileName;
        this.mimeType = mimeType;
    }

    @Override
    public String getName() {
        return "file";
    }

    @Override
    public String getOriginalFilename() {
        return this.fileName;
    }

    @Override
    public String getContentType() {
        return this.mimeType;
    }

    @Override
    public boolean isEmpty() {
        return input == null || input.length == 0;
    }

    @Override
    public long getSize() {
        return input.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return input;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(input);
    }

    @Override
    public void transferTo(File destination) throws IOException, IllegalStateException {
        try (FileOutputStream fos = new FileOutputStream(destination)) {
            fos.write(input);
        }
    }
}
