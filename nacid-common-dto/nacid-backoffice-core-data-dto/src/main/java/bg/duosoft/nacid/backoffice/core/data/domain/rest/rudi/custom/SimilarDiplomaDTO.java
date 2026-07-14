package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseSpecialityDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class SimilarDiplomaDTO {
    private Integer apnId;
    private String number;
    private String diplomaOwnerFirstName;
    private String diplomaOwnerMiddleName;
    private String diplomaOwnerLastName;
    private String diplomaOwnerCivilId;
    private String diplomaOwnerEan;
    private String country;
    private String university;
    private String eduLevel;
    private String eduLevelTranslated;
    private List<TrainingCourseSpecialityDTO> specialities;
    private Integer graduationYear;
    private String appSubTypeCode;
}
