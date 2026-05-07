package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RPProfessionExperienceDTO {
    private Integer id;
    private String professionName;
    private Integer years;
    private Integer months;
    private Integer days;
    private List<RPProfessionExperienceDocumentDTO> documents;
    private ProfessionExperienceExaminationDTO professionExperienceExamination;
}
