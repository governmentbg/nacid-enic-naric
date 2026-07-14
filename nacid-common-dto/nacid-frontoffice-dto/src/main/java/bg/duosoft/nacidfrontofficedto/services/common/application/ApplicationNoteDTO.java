package bg.duosoft.nacidfrontofficedto.services.common.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 14.08.2023
 * Time: 14:07
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationNoteDTO {

    private Integer id;
    private Integer applicationId;
    private String noteText;
    private LocalDateTime dateCreated;
    private String userCreated;
    private LocalDateTime dateUpdated;
    private String userUpdated;
    private String userCreatedFullName;
    private String userUpdatedFullName;
}
