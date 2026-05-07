package bg.duosoft.nacidshareddto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PreviousSubmittedAppResultDTO {
    private List<PreviousSubmittedAppDTO> applications;
    private boolean hasWarning;
    private String warningMessageCode;
}
