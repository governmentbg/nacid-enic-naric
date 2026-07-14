package bg.duosoft.nacidfrontofficedto.user.account;

import bg.duosoft.nacidfrontofficedto.user.NacidUserDetailsDTO;
import lombok.Data;

@Data
public class UserRegistrationDTO extends NacidUserDetailsDTO {

    private String captchaToken;
    private String confirmPassword;
}
