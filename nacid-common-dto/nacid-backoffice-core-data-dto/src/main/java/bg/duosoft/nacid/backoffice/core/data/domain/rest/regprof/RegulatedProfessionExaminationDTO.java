package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegulatedProfessionExaminationDTO {
    private Integer applicationId;
    private CountryDTO country;
    private LocalDate examinationDate;
    private String notes;
    private String userCreated;
    private String profession;
    private Boolean regulatedFlag;
}
