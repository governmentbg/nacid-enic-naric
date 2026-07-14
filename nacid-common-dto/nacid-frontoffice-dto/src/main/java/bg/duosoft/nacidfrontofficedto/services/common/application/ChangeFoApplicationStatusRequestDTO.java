package bg.duosoft.nacidfrontofficedto.services.common.application;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 28.08.2023
 * Time: 9:51
 */
@Data
public class ChangeFoApplicationStatusRequestDTO {
    private Integer applicationId;
    private FoApplicationStatusChangeType statusChangeType;
    private String initiatingUser;
    private String message;
}
