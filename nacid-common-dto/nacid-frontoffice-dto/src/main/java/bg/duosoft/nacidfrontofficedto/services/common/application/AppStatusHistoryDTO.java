package bg.duosoft.nacidfrontofficedto.services.common.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 25.01.2023
 * Time: 16:47
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppStatusHistoryDTO {

    private FoApplicationStatus foStatus;
    private String statusName;
    private LocalDateTime dateCreated;
    private String reasonMessage;
    private String userCreated;
}
