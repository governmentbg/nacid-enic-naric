package bg.duosoft.nacidminioservices.service;


import bg.duosoft.nacidminiodto.FileStoreEntryBaseDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.06.2022
 * Time: 11:16
 */
public interface FileStoreService {
    FileStoreEntryBaseDTO saveNewFile(String fileGroup, String pointer, FileStoreEntryBaseDTO fileStoreEntry);
    FileStoreEntryBaseDTO getFileStoreEntryDetailsAndContent(String rootDirectory, String relativePath, String fileId);
    FileStoreEntryBaseDTO getFileStoreEntryDetailsOnly(String rootDirectory, String relativePath, String fileId);
    FileStoreEntryBaseDTO moveFile(String rootDirectoryNew, String relativePathNew, FileStoreEntryBaseDTO entryToMove, Boolean removeOriginal);
    void copyFile(String rootDirectoryNew, String relativePathNew, String rootDirectoryOld, String relativePathOld, String fileId);
    void removeFile(String rootDirectory, String relativePath, String fileId);
    boolean fileExists(String rootDirectory, String relativePath, String fileId);
}
