package bg.duosoft.nacidfrontofficedto.services.common.document;

import bg.duosoft.nacidfrontofficedto.file.FileStoreEntryDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.FoApplicationStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.01.2023
 * Time: 18:28
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationReceiptDTO {

    private FileStoreEntryDTO file;
    private FoApplicationStatus status;
    private Boolean active;
    private LocalDateTime dateCreated;
    private String signedFileUuid;
    private Boolean customerSigned;

    public ApplicationReceiptDTO(FileStoreEntryDTO file, FoApplicationStatus status, Boolean active, LocalDateTime dateCreated, String signedFileUuid) {
        this.file = file;
        this.status = status;
        this.active = active;
        this.dateCreated = dateCreated;
        this.signedFileUuid = signedFileUuid;
    }

    public ApplicationReceiptDTO(FileStoreEntryDTO file, FoApplicationStatus status, Boolean active, LocalDateTime dateCreated) {
        this.file = file;
        this.status = status;
        this.active = active;
        this.dateCreated = dateCreated;
    }
}

