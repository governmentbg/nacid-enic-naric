package bg.duosoft.nacidminioservices.utils;

import bg.duosoft.nacidminiodto.FileStoreEntryBaseDTO;
import bg.duosoft.nacidshareddata.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileUtils {
    public static FileStoreEntryBaseDTO createFileStoreEntry(MultipartFile uploadedFile, String rootDirectory, String relativePath) throws IOException {
        FileStoreEntryBaseDTO entry = new FileStoreEntryBaseDTO();
        entry.setContent(uploadedFile.getBytes());
        entry.setFileName(uploadedFile.getOriginalFilename());
        entry.setFileSize(uploadedFile.getSize());
        entry.setContentType(MimeTypeUtils.guessMimeFromBytes(uploadedFile.getBytes(), uploadedFile.getOriginalFilename()));
        entry.setRootDirectory(rootDirectory);
        entry.setRelativePath(relativePath);
        return entry;
    }
}
