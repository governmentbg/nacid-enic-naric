package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrainingCourseUniversityDTO {
    private UniversityDTO university;
    private CountryDTO country;
    private String universityNameTranslated;
    private String universityContact;
    private Integer ordNum;
    private FacultyDTO faculty;
}
