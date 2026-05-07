package bg.duosoft.nacidfrontofficedto.services.common.application;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.10.2023
 * Time: 13:56
 */
@Data
public class ApplicationCorrespondenceDTO {

    private Integer id;
    private Integer applicationId;
    private Integer refId;
    private String tempNumber;
    private ApplicationSubtype applicationSubtype;
    private String applicationSubtypeName;
    private String about;
    private String registrationNumber;
    private LocalDate registrationDate;
    private LocalDateTime dateCreated;
    private LocalDateTime dateRead;
}
