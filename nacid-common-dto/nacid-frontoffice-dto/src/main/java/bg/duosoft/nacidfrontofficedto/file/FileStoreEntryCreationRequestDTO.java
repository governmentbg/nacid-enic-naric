package bg.duosoft.nacidfrontofficedto.file;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.07.2022
 * Time: 14:29
 */
@Data
public class FileStoreEntryCreationRequestDTO {

    private String captchaToken;
    private String remoteIp;
    private FileStoreEntryDTO fileStoreEntry;

}
