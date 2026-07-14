package bg.duosoft.nacidfrontofficedto.services.common.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.06.2022
 * Time: 14:50
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentDetailsDTO {

    private List<AttachedDocumentDTO> attachments;
}
