package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRecognizedSpecialityDTO {
    private Integer id;
    private Integer applicationId;
    private String speciality;
}
