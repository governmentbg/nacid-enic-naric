package bg.duosoft.nacidfrontofficedto.user.account;

import bg.duosoft.nacidfrontofficedto.TemporaryKeyStatusType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasswordTemporaryKeyResponseDTO {
    private TemporaryKeyStatusType status;
    private String user;
}
