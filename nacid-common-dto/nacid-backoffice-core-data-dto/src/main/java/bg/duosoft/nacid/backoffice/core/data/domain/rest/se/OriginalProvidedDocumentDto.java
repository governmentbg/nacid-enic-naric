package bg.duosoft.nacid.backoffice.core.data.domain.rest.se;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.OriginalProvidedMethod;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OriginalProvidedDocumentDto {
    private Integer id;
    private Integer applicationId;
    private Integer efilingId;
    private LocalDate receivedDate;
    private LocalDate returnedDate;
    private String fileLocation;
    private String documentNote;
    private String internalNote;
    private OriginalProvidedMethod method;
}
