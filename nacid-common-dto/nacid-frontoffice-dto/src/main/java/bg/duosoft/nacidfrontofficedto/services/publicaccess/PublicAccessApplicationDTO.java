package bg.duosoft.nacidfrontofficedto.services.publicaccess;

import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.08.2023
 * Time: 13:22
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublicAccessApplicationDTO extends CommonApplicationDTO {

    private PublicAccessDetailsDTO publicAccessDetails;
}
