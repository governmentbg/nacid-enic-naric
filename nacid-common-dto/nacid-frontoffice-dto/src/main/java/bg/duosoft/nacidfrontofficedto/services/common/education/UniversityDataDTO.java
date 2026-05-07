package bg.duosoft.nacidfrontofficedto.services.common.education;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.06.2022
 * Time: 11:20
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UniversityDataDTO {

    private Float key;
    private String name;
    private Integer nameId;
    private String faculty;
    private Integer facultyId;
    private String universityContact;
}
