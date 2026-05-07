package bg.duosoft.nacidfrontofficedto.services.publicaccess;

import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.08.2023
 * Time: 13:22
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublicAccessDetailsDTO {

    private String about;
    private String comment;
    private List<ReferenceDataDTO> infoForms;

}
