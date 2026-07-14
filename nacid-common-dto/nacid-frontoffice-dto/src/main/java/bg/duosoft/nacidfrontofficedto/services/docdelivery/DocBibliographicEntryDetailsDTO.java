package bg.duosoft.nacidfrontofficedto.services.docdelivery;

import bg.duosoft.nacidfrontofficedto.file.FileStoreEntryDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.services.common.WithFile;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.11.2022
 * Time: 15:09
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocBibliographicEntryDetailsDTO implements WithFile {

    private FileStoreEntryDTO file;
    private String bibliographicDataText;
    private Boolean electronicCatalogues;
    private Boolean bgLibraries;
    private Boolean foreignLibraries;
    private ReferenceDataDTO deliveryResultKind;
    private boolean forRemoval;
}
