package bg.duosoft.nacidfrontofficedto.user.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForgottenUserDataDTO {
    private String email;
    private String captchaToken;
}
