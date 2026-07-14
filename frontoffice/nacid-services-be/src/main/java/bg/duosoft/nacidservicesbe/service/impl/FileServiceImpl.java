package bg.duosoft.nacidservicesbe.service.impl;

import bg.duosoft.nacidcoreclient.client.fileStore.AdminFileStoreClient;
import bg.duosoft.nacidcoreclient.client.fileStore.FileStoreClient;
import bg.duosoft.nacidminiodto.util.FileConstants;
import bg.duosoft.nacidfrontofficedto.file.FileStoreEntryDTO;
import bg.duosoft.nacidservicesbe.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 17.06.2022
 * Time: 12:12
 */
@Service("fileService")
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileStoreClient fileStoreClient;
    private final AdminFileStoreClient adminFileStoreClient;

    @Override
    public FileStoreEntryDTO uploadFile(String fileGroup, FileStoreEntryDTO entry) {
        return adminFileStoreClient.saveNewFileRestricted(fileGroup, FileConstants.DEFAULT_FILE_POINTER, entry);
    }

    @Override
    public FileStoreEntryDTO uploadFile(String fileGroup, byte[] receiptBytes, String fileName, String contentType, String relativePath) {
        FileStoreEntryDTO entry = new FileStoreEntryDTO();
        entry.setFileName(fileName);
        entry.setRelativePath(relativePath);
        entry.setRootDirectory(FileConstants.SERVICES_ROOT_DIRECTORY);
        entry.setFileSize((long)receiptBytes.length);
        entry.setContent(receiptBytes);
        entry.setContentType(contentType);

        return uploadFile(fileGroup, entry);
    }

    @Override
    public FileStoreEntryDTO getFileDetailsAndContent(String rootDirectory, String relativePath, String fileId) {
        return fileStoreClient.getFileDetailsAndContent(rootDirectory, relativePath, fileId);
    }

    @Override
    public byte[] getFileContent(String rootDirectory, String relativePath, String fileId, String disposition) {
        return fileStoreClient.getFileContent(rootDirectory, relativePath, fileId, disposition).getBody();
    }

    @Override
    public FileStoreEntryDTO moveFileToPersistentStore(String rootDirectoryNew, String relativePathNew, boolean removeOriginal, FileStoreEntryDTO entry) {
        return adminFileStoreClient.moveFile(rootDirectoryNew, relativePathNew, removeOriginal, entry);
    }

    @Override
    public void removeFile(String rootDirectory, String relativePath, String fileId) {
        adminFileStoreClient.removeFile(rootDirectory, relativePath, fileId);
    }

    @Override
    public boolean fileExists(String rootDirectory, String relativePath, String fileId) {
        return adminFileStoreClient.getFileExists(rootDirectory, relativePath, fileId);
    }
}
