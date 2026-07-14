package bg.duosoft.nacidfrontofficedto.services.common.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.01.2023
 * Time: 11:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationCheckupRequestDTO {

    private String dossierNumber;
    private String accessCode;
    private String captchaToken;
    private String remoteIp;
}
