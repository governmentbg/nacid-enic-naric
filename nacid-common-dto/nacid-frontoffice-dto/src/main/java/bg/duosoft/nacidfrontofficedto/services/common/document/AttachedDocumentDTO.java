package bg.duosoft.nacidfrontofficedto.services.common.document;

import bg.duosoft.nacidfrontofficedto.file.FileStoreEntryDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.DocTypeDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.services.common.WithFile;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.06.2022
 * Time: 14:51
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachedDocumentDTO implements WithFile {

    private DocTypeDTO attachmentType;
    private String description;
    private ReferenceDataDTO attachmentForm;
    private FileStoreEntryDTO file;
    private boolean forRemoval;
}
