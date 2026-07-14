package bg.duosoft.nacidfrontofficedto.services.serecognition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecEduAdditionalSubjectsDTO {
    private String additionalStudiedSubjectsNote;
    private List<SecEduStudiedSubjectDTO> additionalStudiedSubjects;
    private String additionalStudiedSubjectsSchoolData;
}
