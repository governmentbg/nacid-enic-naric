package bg.duosoft.nacidfrontofficedto.services.docdelivery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.11.2022
 * Time: 15:09
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocBibliographicDetailsDTO {

    private List<DocBibliographicEntryDetailsDTO> entries;
}
