package bg.duosoft.nacidfrontofficedto.services.serecognition;

import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecEduRecPurposeDTO {
    private String notes;
    private ReferenceDataDTO purpose;
}
