package bg.duosoft.nacidfrontofficedto.services.biblioreference;

import bg.duosoft.nacidfrontofficedto.nomenclature.LanguageDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.11.2022
 * Time: 14:57
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BibliographicReferenceDetailsDTO {

    private Boolean foreignSearch;
    private Boolean nacidSearch;
    private BibliographicReferenceResultKind foreignSearchKind;
    private BibliographicReferenceResultKind nacidSearchKind;
    private String theme;
    private String keywords;
    private String searchFrom;
    private String searchTo;
    private List<LanguageDTO> searchLanguages;
}
