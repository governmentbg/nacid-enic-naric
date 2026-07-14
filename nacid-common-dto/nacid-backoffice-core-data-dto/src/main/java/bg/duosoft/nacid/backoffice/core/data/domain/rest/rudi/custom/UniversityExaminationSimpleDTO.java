package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName = "newInstance")
public class UniversityExaminationSimpleDTO {
    private Integer id;
    private LocalDate examinationDate;
    private Boolean isRecognized;
    private String notes;
}
