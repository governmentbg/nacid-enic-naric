package bg.duosoft.nacidfrontofficedto.user.account;

import lombok.Data;

@Data
public class ChangePasswordDTO {
    private String password;
    private String confirmPassword;
}
