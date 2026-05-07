package bg.duosoft.nacidcoredata.util;

import bg.duosoft.nacidfrontofficedto.file.FileStoreEntryDTO;
import bg.duosoft.nacidminiodto.util.FileConstants;
import org.springframework.data.util.Pair;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileUtils {
    public static Pair<String, String> separateFilePath(String filePath) {
        int index = filePath.lastIndexOf(FileConstants.PATH_SEPARATOR);
        String relativePath = filePath.substring(0, index);
        String fileId = filePath.substring(index+1);

        return Pair.of(relativePath, fileId);
    }

    public static boolean isFilePersisted(FileStoreEntryDTO fileStoreEntry){
        return !fileStoreEntry.getRootDirectory().equals(FileConstants.TEMP_ROOT_DIRECTORY);
    }

    public static FileStoreEntryDTO createFileStoreEntry(MultipartFile uploadedFile, String rootDirectory, String relativePath) throws IOException {
        FileStoreEntryDTO entry = new FileStoreEntryDTO();
        entry.setContent(uploadedFile.getBytes());
        entry.setFileName(uploadedFile.getOriginalFilename());
        entry.setFileSize(uploadedFile.getSize());
        entry.setContentType(uploadedFile.getContentType());
        entry.setRootDirectory(rootDirectory);
        entry.setRelativePath(relativePath);
        return entry;
    }
}
