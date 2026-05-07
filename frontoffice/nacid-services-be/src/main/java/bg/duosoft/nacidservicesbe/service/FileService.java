package bg.duosoft.nacidservicesbe.service;

import bg.duosoft.nacidfrontofficedto.file.FileStoreEntryDTO;


/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 08.06.2022
 * Time: 16:27
 */
public interface FileService {

    FileStoreEntryDTO uploadFile(String fileGroup, FileStoreEntryDTO entry);
    FileStoreEntryDTO uploadFile(String fileGroup, byte[] receiptBytes, String fileName, String contentType, String relativePath);
    FileStoreEntryDTO getFileDetailsAndContent(String rootDirectory, String relativePath, String fileId);
    byte[] getFileContent(String rootDirectory, String relativePath, String fileId, String disposition);
    FileStoreEntryDTO moveFileToPersistentStore(String rootDirectoryNew, String relativePathNew, boolean removeOriginal, FileStoreEntryDTO entry);
    void removeFile(String rootDirectory, String relativePath, String fileId);
    boolean fileExists(String rootDirectory, String relativePath, String fileId);
}
