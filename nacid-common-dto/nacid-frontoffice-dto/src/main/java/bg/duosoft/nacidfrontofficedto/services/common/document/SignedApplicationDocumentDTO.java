package bg.duosoft.nacidfrontofficedto.services.common.document;

import bg.duosoft.nacidfrontofficedto.file.FileStoreEntryDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.FoApplicationStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.02.2023
 * Time: 15:15
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignedApplicationDocumentDTO {
    private FileStoreEntryDTO file;
    private FoApplicationStatus status = FoApplicationStatus.SUBMITTED_WITH_SIGNATURE;
}
