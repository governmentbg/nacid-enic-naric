package bg.duosoft.nacidfrontofficedto.email;

import bg.duosoft.nacidfrontofficedto.TemporaryKeyStatusType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailTemporaryKeyResponseDTO {
    private TemporaryKeyStatusType status;
    private String newEmail;
}
