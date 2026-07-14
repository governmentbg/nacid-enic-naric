package bg.duosoft.nacidfrontofficedto.services.officialnotes;

import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.services.common.education.WithServiceType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.11.2022
 * Time: 15:20
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfficialNotesDetailsDTO implements WithServiceType {

    private List<OfficialNoteKind> officialNotesKinds;
    private String additionalInformation;
    private ReferenceDataDTO serviceType;
}
