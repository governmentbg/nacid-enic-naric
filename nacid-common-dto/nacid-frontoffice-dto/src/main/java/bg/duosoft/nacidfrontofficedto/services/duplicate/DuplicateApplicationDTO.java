package bg.duosoft.nacidfrontofficedto.services.duplicate;

import bg.duosoft.nacidfrontofficedto.services.common.application.AdditionalDocBaseApplicationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DuplicateApplicationDTO extends AdditionalDocBaseApplicationDTO {
    private DuplicateDetailDTO duplicateDetail;
}
