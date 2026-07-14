package bg.duosoft.nacidfrontofficedto.services.common;

import bg.duosoft.nacidfrontofficedto.file.FileStoreEntryDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 31.03.2023
 * Time: 14:32
 */
public interface WithFile {

    FileStoreEntryDTO getFile();

    void setFile(FileStoreEntryDTO file);

    boolean isForRemoval();

    void setForRemoval(boolean forRemoval);
}
