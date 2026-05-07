package bg.duosoft.nacidfrontofficedto.user.account;

import lombok.Data;

@Data
public class ResetPasswordDTO {
    private String password;
    private String confirmPassword;
    private String captchaToken;
    private String key;
}
