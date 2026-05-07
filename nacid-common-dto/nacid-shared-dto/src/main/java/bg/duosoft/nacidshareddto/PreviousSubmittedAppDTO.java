package bg.duosoft.nacidshareddto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PreviousSubmittedAppDTO {
    private String entryNumber;
    private LocalDate entryDate;
    private String statusName;
    private String serviceName;
}
