package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentRegistrationResultDTO {
    private Integer docId;
    private String entryNumber;
    private LocalDate entryDate;
}
