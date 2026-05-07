package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RPProfessionExperienceDocumentDateDTO {
    private Integer id;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private ReferenceDataDTO workdayDuration;
}
