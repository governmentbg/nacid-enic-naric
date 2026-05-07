package bg.duosoft.nacidfrontofficedto.services.serecognition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecEduStudiedSubjectDTO {
    private String subjectName;
    private String translatedSubjectName;
    private String classYear;
    private String diplomaGrade;
}
