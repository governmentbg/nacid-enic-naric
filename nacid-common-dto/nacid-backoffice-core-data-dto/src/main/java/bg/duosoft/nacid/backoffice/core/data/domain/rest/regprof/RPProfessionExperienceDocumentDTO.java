package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ProfessionExperienceDocumentTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RPProfessionExperienceDocumentDTO {
    private Integer id;
    private String documentNumber;
    private String documentIssuer;
    private String documentDate;
    private ProfessionExperienceDocumentTypeDTO professionExperienceDocumentType;
    private List<RPProfessionExperienceDocumentDateDTO> dates;
    private Boolean experienceCalculationFlag;
}
