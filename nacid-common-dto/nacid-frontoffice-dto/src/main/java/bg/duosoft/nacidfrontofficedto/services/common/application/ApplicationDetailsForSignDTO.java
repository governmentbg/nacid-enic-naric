package bg.duosoft.nacidfrontofficedto.services.common.application;

import bg.duosoft.nacidfrontofficedto.file.FileStoreEntryDTO;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.02.2023
 * Time: 16:48
 */
@Data
public class ApplicationDetailsForSignDTO {

    private Integer id;
    private ApplicationSubtype applicationSubtype;
    private FileStoreEntryDTO receipt;
}
